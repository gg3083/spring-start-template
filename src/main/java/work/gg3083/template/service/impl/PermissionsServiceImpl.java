package work.gg3083.template.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import work.gg3083.template.commom.Const;
import work.gg3083.template.entity.Permissions;
import work.gg3083.template.entity.param.PermAddParam;
import work.gg3083.template.entity.param.PermUpdateParam;
import work.gg3083.template.entity.vo.PageInfo;
import work.gg3083.template.exception.CustomException;
import work.gg3083.template.mapper.PermissionsMapper;
import work.gg3083.template.service.IPermissionsService;

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
    public PageInfo<Permissions> list4Page(Integer pageNo, Integer pageSize, String searchKey) {
        Page<Permissions> page = new Page<>(pageNo,pageSize);
        page.setRecords(permissionsMapper.list4Page(searchKey,page));
        return new PageInfo<>(page);
    }

    @Override
    public int update(PermUpdateParam param) {
        if (StringUtils.isEmpty(param.getId())) {
            throw new CustomException("ID不能为空");
        }
        Permissions permissions = new Permissions()
                .setId(param.getId())
                .setIcon(param.getIcon())
                .setPermName(param.getPermName())
                .setParentId(param.getParentId())
                .setUrl(param.getUrl());
        return permissionsMapper.updateById(permissions);
    }

    @Override
    public int add(PermAddParam param) {
        Permissions permissions = new Permissions()
                .setIcon(param.getIcon())
                .setPermName(param.getPermName())
                .setParentId(param.getParentId())
                .setUrl(param.getUrl())
                .setPermAlias(param.getPermAlias());
        return permissionsMapper.insert(permissions);
    }

    @Override
    public Permissions get(Integer id) {
        return this.getById(id);
    }

    @Override
    public int delete(Integer id) {
        Permissions permissions = new Permissions()
                .setId(id)
                .setDeleteStatus(Const.DELETE_STATUS_Y);
        return permissionsMapper.updateById(permissions);
    }
}
