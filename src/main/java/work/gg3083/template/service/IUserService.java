package work.gg3083.template.service;

import com.baomidou.mybatisplus.extension.service.IService;
import work.gg3083.template.entity.User;
import work.gg3083.template.entity.vo.UserVO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Gimi
 * @since 2019-08-15
 */
public interface IUserService extends IService<User> {

    User findUserByLoginName();

    UserVO findUserVoByLoginName(String loginName);

    void register(String loginName,String password);
}
