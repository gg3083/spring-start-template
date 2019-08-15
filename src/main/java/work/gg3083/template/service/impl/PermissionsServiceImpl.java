package work.gg3083.template.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import work.gg3083.template.entity.Permissions;
import work.gg3083.template.mapper.PermissionsMapper;
import work.gg3083.template.service.IPermissionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author Gimi
 * @since 2019-08-15
 */
@Service
public class PermissionsServiceImpl extends ServiceImpl<PermissionsMapper, Permissions> implements IPermissionsService {

    @Autowired
    private PermissionsMapper permissionsMapper;

    @Override
    public List<Permissions> findPermByLoginName(String loginName) {
        return permissionsMapper.findPermByLoginName(loginName);
    }
}
