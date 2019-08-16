package work.gg3083.template.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import work.gg3083.template.commom.Const;
import work.gg3083.template.entity.Role;
import work.gg3083.template.entity.param.RoleAddParam;
import work.gg3083.template.entity.param.RoleUpdateParam;
import work.gg3083.template.entity.vo.PageInfo;
import work.gg3083.template.exception.CustomException;
import work.gg3083.template.mapper.RoleMapper;
import work.gg3083.template.service.IRoleService;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Gimi
 * @since 2019-08-15
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int add(RoleAddParam param) {
        Role role = new Role()
                .setRoleName(param.getRoleName())
                .setRoleAlias(param.getRoleAlias());
        return roleMapper.insert(role);
    }

    @Override
    public int update(RoleUpdateParam param) {
        if (StringUtils.isEmpty(param.getId())) {
            throw new CustomException("ID不能为空");
        }
        Role role = new Role()
                .setId(param.getId())
                .setRoleName(param.getRoleName())
                .setRoleAlias(param.getRoleAlias());
        return roleMapper.updateById(role);
    }

    @Override
    public Role get(Integer id) {
        return this.getById(id);
    }

    @Override
    public PageInfo<Role> list4Page(Integer pageNo, Integer pageSize, String searchKey) {
        Page<Role> page = new Page<>(pageNo,pageSize);
        page.setRecords(roleMapper.list4Page(searchKey,page));
        return new PageInfo<>(page);
    }

    @Override
    public int delete(Integer id) {
        Role role = new Role()
                .setId(id)
                .setDeleteStatus(Const.DELETE_STATUS_Y);
        return roleMapper.updateById(role);
    }
}
