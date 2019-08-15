package work.gg3083.template.service;

import com.baomidou.mybatisplus.extension.service.IService;
import work.gg3083.template.entity.Permissions;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author Gimi
 * @since 2019-08-15
 */
public interface IPermissionsService extends IService<Permissions> {

    List<Permissions> findPermByLoginName(String loginName);
}
