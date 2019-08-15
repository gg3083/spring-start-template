package work.gg3083.template.mapper;

import work.gg3083.template.entity.Permissions;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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
}
