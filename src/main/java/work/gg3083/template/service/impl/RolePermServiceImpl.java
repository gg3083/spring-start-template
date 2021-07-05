package work.gg3083.template.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.val;
import work.gg3083.template.entity.RolePerm;
import work.gg3083.template.entity.param.RolePermAddParam;
import work.gg3083.template.entity.vo.RolePermVO;
import work.gg3083.template.mapper.RolePermMapper;
import work.gg3083.template.service.IRolePermService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色权限关联表 服务实现类
 * </p>
 *
 * @author Gimi
 * @since 2019-08-15
 */
@Service
public class RolePermServiceImpl extends ServiceImpl<RolePermMapper, RolePerm> implements IRolePermService {



    @Override
    public List<Integer> save(RolePermAddParam param, Integer roleId) {
        LambdaQueryWrapper<RolePerm> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(RolePerm::getRoleId, roleId);
        this.remove(lambdaQueryWrapper);
        List<RolePerm> rolePermList = new ArrayList<>();
        param.getPermIdList().forEach(item->{
            RolePerm rolePerm = new RolePerm();
            rolePerm.setRoleId(roleId);
            rolePerm.setPermId(item);
            rolePermList.add(rolePerm);
        });
        if (this.saveBatch(rolePermList)) {
            return param.getPermIdList();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Integer> select(Integer roleId) {
        LambdaQueryWrapper<RolePerm> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(RolePerm::getRoleId, roleId);
        return this.list(lambdaQueryWrapper).stream().map(RolePerm::getPermId).collect(Collectors.toList());
    }

    @Override
    public List<RolePermVO> selectAll() {
        List<RolePerm> list = this.list();
        Map<Integer, List<RolePerm>> collect = list.stream().collect(Collectors.groupingBy(RolePerm::getRoleId));
        List<RolePermVO> res = new ArrayList<>();
        collect.forEach((key, item)->{
            RolePermVO vo = new RolePermVO();
            vo.setRoleId(key);
            vo.setPremId(item.stream().map(RolePerm::getPermId).collect(Collectors.toList()));
            res.add(vo);
        });
        return res;
    }
}
