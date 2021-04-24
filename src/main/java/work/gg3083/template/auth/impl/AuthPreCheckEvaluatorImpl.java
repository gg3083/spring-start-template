package work.gg3083.template.auth.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import work.gg3083.template.auth.AuthPreCheckEvaluator;
import work.gg3083.template.mapper.UserMapper;


@Service("authPreCheckEvaluatorImpl2")
@Slf4j
public class AuthPreCheckEvaluatorImpl implements AuthPreCheckEvaluator {

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean check(UsernamePasswordAuthenticationToken s) {
        log.info("开始校验权限 {}", s.toString());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if ( authentication.getPrincipal() instanceof User){
            User user = (User)authentication.getPrincipal();
            String username = user.getUsername();
            return loadByPerm(username,"s");
        }
        return false;
    }

    public boolean loadByPerm(String userName , String prem){
        return true;
    }
}
