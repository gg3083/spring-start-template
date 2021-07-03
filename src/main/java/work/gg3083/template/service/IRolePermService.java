package work.gg3083.template.service;

import work.gg3083.template.entity.RolePerm;
import com.baomidou.mybatisplus.extension.service.IService;
import work.gg3083.template.entity.param.RolePermAddParam;
import work.gg3083.template.entity.vo.RolePermVO;

import java.util.List;

/**
 * <p>
 * 角色权限关联表 服务类
 * </p>
 *
 * @author Gimi
 * @since 2019-08-15
 */
public interface IRolePermService extends IService<RolePerm> {

    List<Integer> save(RolePermAddParam param, Integer roleId);

    List<Integer> select(Integer roleId);

    List<RolePermVO> selectAll();
}
