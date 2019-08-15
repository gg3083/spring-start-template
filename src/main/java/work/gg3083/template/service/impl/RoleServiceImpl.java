package work.gg3083.template.service.impl;

import work.gg3083.template.entity.Role;
import work.gg3083.template.mapper.RoleMapper;
import work.gg3083.template.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
