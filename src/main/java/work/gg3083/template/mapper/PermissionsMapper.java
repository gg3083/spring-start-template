package work.gg3083.template.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import work.gg3083.template.entity.Permissions;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author Gimi
 * @since 2019-08-15
 */
public interface PermissionsMapper extends BaseMapper<Permissions> {

    List<Permissions> findPermByLoginName(String loginName);

    List<Permissions> list4Page(@Param("searchKey") String searchKey, Page<Permissions> page);
}
