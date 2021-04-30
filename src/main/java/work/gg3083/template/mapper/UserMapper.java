package work.gg3083.template.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import work.gg3083.template.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import work.gg3083.template.entity.vo.UserVO;

import java.util.List;

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

    UserVO findUserVoByUserId(String userId);

    List<User> list4Page(@Param("searchKey") String searchKey, Page<User> page);
}
