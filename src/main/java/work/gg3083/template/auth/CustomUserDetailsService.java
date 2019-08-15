package work.gg3083.template.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import work.gg3083.template.entity.vo.UserVO;
import work.gg3083.template.exception.GimiException;
import work.gg3083.template.service.IUserService;

import java.util.ArrayList;
import java.util.Collection;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String loginName){
        UserVO userVo = userService.findUserVoByLoginName(loginName);
        if ( userVo == null) {
            throw new GimiException("用户不存在");
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userVo.getRoleAlias()));
        return new org.springframework.security.core.userdetails.User(userVo.getLoginName(), userVo.getPassword(), authorities);
    }
}