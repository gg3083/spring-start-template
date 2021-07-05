package work.gg3083.template.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import work.gg3083.template.entity.vo.UserVO;
import work.gg3083.template.exception.MyException;
import work.gg3083.template.service.IUserService;

import java.util.ArrayList;
import java.util.Collection;


@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String loginName){
        if (StringUtils.isEmpty(loginName)){
            throw new MyException("登录用户名不能为空！");
        }
        log.info("当前想登录的用户为：{}", loginName);
        UserVO userVo = userService.findUserVoByLoginName(loginName);
        if ( userVo == null) {
            throw new MyException("用户不存在！");
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(String.valueOf(userVo.getRoleId())));
        return new org.springframework.security.core.userdetails.User(userVo.getLoginName(), userVo.getPassword(), authorities);
    }
}