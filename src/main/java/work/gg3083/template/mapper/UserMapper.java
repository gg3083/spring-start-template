package work.gg3083.template.mapper;

import work.gg3083.template.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import work.gg3083.template.entity.vo.UserVO;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Gimi
 * @since 2019-08-15
 */
public interface UserMapper extends BaseMapper<User> {

    UserVO findUserVoByLoginName(String loginName);
}
