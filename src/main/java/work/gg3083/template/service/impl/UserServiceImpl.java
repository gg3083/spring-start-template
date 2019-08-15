package work.gg3083.template.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import work.gg3083.template.entity.User;
import work.gg3083.template.entity.UserRole;
import work.gg3083.template.entity.vo.UserVO;
import work.gg3083.template.mapper.UserMapper;
import work.gg3083.template.mapper.UserRoleMapper;
import work.gg3083.template.service.IUserService;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Gimi
 * @since 2019-08-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;


    @Override
    public User findUserByLoginName() {
        return null;
    }

    @Override
    public UserVO findUserVoByLoginName(String loginName) {
        return userMapper.findUserVoByLoginName(loginName);
    }

    @Override
    public void register(String loginName, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePwd = encoder.encode(password);
        User user = new User()
            .setLoginName(loginName)
            .setPassword(encodePwd);
        userMapper.insert(user);
        UserRole userRole = new UserRole()
                .setRoleId(2)
                .setUserId(user.getId());
        userRoleMapper.insert(userRole);
    }
}
