package work.gg3083.template.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import work.gg3083.template.commom.CommonConst;
import work.gg3083.template.entity.RolePerm;
import work.gg3083.template.entity.User;
import work.gg3083.template.entity.UserRole;
import work.gg3083.template.entity.param.UserAddParam;
import work.gg3083.template.entity.param.UserUpdateParam;
import work.gg3083.template.entity.vo.PageInfo;
import work.gg3083.template.entity.vo.UserAdminVO;
import work.gg3083.template.entity.vo.UserVO;
import work.gg3083.template.exception.MyException;
import work.gg3083.template.mapper.RolePermMapper;
import work.gg3083.template.mapper.UserMapper;
import work.gg3083.template.mapper.UserRoleMapper;
import work.gg3083.template.service.IUserRoleService;
import work.gg3083.template.service.IUserService;

import java.util.List;
import java.util.stream.Collector;

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

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private RolePermMapper rolePermMapper;


    @Override
    public UserVO findUserVoByLoginName(String loginName) {
        UserVO userVO = userMapper.findUserVoByLoginName(loginName);
        if (userVO != null){
            List<String> rolePerms = rolePermMapper.queryPermStringByRoleId(userVO.getRoleId());
            userVO.setPermList(rolePerms);
        }
        return userVO;
    }

    @Override
    public void register(String loginName, String password) {
        if (findUserVoByLoginName(loginName) != null){
            throw new MyException("用户名重复！");
        }
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
        //TODO 待修改
    }

    @Override
    public PageInfo<UserAdminVO> list4Page(Integer pageNo, Integer pageSize, String searchKey) {
        Page<UserAdminVO> page = new Page<>(pageNo,pageSize);
        page.setRecords(userMapper.list4Page(searchKey,page));
        return new PageInfo<>(page);
    }

    @Override
    public void add(UserAddParam param) {
        if (StringUtils.isEmpty(param.getPassword())){
            param.setPassword(CommonConst.DEFAULT_PASSWORD);
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePwd = encoder.encode(param.getPassword());
        User user = new User()
                .setLoginName(param.getLoginName())
                .setRealName(param.getRealName())
                .setPassword(encodePwd)
                .setAddress(param.getAddress())
                .setBirthDay(param.getBirthDay())
                .setTelephone(param.getTelephone())
                .setEmail(param.getEmail())
                .setEnglishName(param.getEnglishName())
                .setHeadImg(param.getHeadImg())
                .setJobNo(param.getJobNo())
                .setGender(param.getGender());
        if (this.save(user)){
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(param.getRoleId());
            userRoleService.save(userRole);
        }
    }

    @Override
    public void update(Integer id, UserUpdateParam param) {

        User user = new User()
                .setId(id)
                .setAddress(param.getAddress())
                .setBirthDay(param.getBirthDay())
                .setTelephone(param.getTelephone())
                .setEmail(param.getEmail())
                .setEnglishName(param.getEnglishName())
                .setHeadImg(param.getHeadImg())
                .setJobNo(param.getJobNo())
                .setRealName(param.getRealName())
                .setGender(param.getGender());
        if (userMapper.updateById(user)>0){
            LambdaQueryWrapper<UserRole> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(UserRole::getUserId, user.getId());
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(param.getRoleId());
            userRoleService.update(userRole, lambdaQueryWrapper);
        }
    }

    @Override
    public User get(Integer id) {
        return this.getById(id);
    }

    @Override
    public int delete(Integer id) {
        return userMapper.deleteById(id);
    }

    @Override
    public UserVO findUserVoByUserId(String id) {
        UserVO userVO = userMapper.findUserVoByUserId(id);
        if (userVO != null){
            List<String> rolePerms = rolePermMapper.queryPermStringByRoleId(userVO.getRoleId());
            userVO.setPermList(rolePerms);
        }
        return userVO;
    }
}
