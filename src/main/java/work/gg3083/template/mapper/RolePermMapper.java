package work.gg3083.template.mapper;

import work.gg3083.template.entity.RolePerm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色权限关联表 Mapper 接口
 * </p>
 *
 * @author Gimi
 * @since 2019-08-15
 */
public interface RolePermMapper extends BaseMapper<RolePerm> {


    List<String> queryPermStringByRoleId(Integer roleId);

}
