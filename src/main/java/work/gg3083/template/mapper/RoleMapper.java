package work.gg3083.template.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import work.gg3083.template.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author Gimi
 * @since 2019-08-15
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> list4Page(@Param("searchKey") String searchKey, Page<Role> page);

    int deleteById(@Param("id")Integer id);
}
