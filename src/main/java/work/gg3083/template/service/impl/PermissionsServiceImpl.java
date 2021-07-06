package work.gg3083.template.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import work.gg3083.template.commom.CommonConst;
import work.gg3083.template.entity.Permissions;
import work.gg3083.template.entity.RolePerm;
import work.gg3083.template.entity.UserRole;
import work.gg3083.template.entity.param.PermAddParam;
import work.gg3083.template.entity.param.PermUpdateParam;
import work.gg3083.template.entity.vo.PageInfo;
import work.gg3083.template.exception.MyException;
import work.gg3083.template.mapper.PermissionsMapper;
import work.gg3083.template.service.IPermissionsService;
import work.gg3083.template.service.IRolePermService;
import work.gg3083.template.service.IRoleService;

import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author Gimi
 * @since 2019-08-15
 */
@Service
public class PermissionsServiceImpl extends ServiceImpl<PermissionsMapper, Permissions> implements IPermissionsService {

    @Autowired
    private PermissionsMapper permissionsMapper;

    @Override
    public List<Permissions> findPermByLoginName(String loginName) {
        return permissionsMapper.findPermByLoginName(loginName);
    }

    @Override
    public PageInfo<Permissions> list(Integer pageNo, Integer pageSize, String searchKey) {
//        Page<Permissions> page = new Page<>(pageNo,pageSize);
        List<Permissions> list = permissionsMapper.list(searchKey);
        return new PageInfo<>(list);
    }

    @Override
    public int update(Integer id, PermUpdateParam param) {

        Permissions permissions = new Permissions()
                .setId(id)
                .setIcon(param.getIcon())
                .setPermName(param.getPermName())
                .setParentId(param.getParentId())
                .setPermAlias(param.getPermAlias())
                .setUrl(param.getUrl());
        return permissionsMapper.updateById(permissions);
    }

    @Override
    public int add(PermAddParam param) {
        final LambdaQueryChainWrapper<Permissions> eq = this.lambdaQuery().eq(Permissions::getPermAlias, param.getPermAlias());
        if (this.list(eq.getWrapper()).size() > 0){
            throw new MyException("重复的key值！");
        }
        Permissions permissions = new Permissions()
                .setIcon(param.getIcon())
                .setPermName(param.getPermName())
                .setParentId(param.getParentId())
                .setUrl(param.getUrl())
                .setPermAlias(param.getPermAlias());
        this.save(permissions);
        return permissions.getId();
    }

    @Override
    public Permissions get(Integer id) {
        return this.getById(id);
    }


    @Autowired
    private IRolePermService rolePermService;


    @Override
    public int delete(Integer id) {
        LambdaQueryChainWrapper<RolePerm> eq = rolePermService.lambdaQuery().eq(RolePerm::getPermId, id);
        if (rolePermService.list(eq.getWrapper()).size() > 0) {
            throw new MyException("该权限下绑定了角色，不允许删除！");
        }
        return permissionsMapper.deleteById(id);
    }
}
