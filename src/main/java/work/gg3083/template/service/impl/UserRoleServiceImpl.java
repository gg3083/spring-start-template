package work.gg3083.template.service.impl;

import work.gg3083.template.entity.UserRole;
import work.gg3083.template.mapper.UserRoleMapper;
import work.gg3083.template.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author Gimi
 * @since 2019-08-15
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
