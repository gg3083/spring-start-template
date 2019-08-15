package work.gg3083.template.auth.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import work.gg3083.template.auth.AuthPreCheckEvaluator;
import work.gg3083.template.mapper.UserMapper;


@Service("authPreCheckEvaluatorImpl")
public class AuthPreCheckEvaluatorImpl implements AuthPreCheckEvaluator {

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean check(String s) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if ( authentication.getPrincipal() instanceof User){
            User user = (User)authentication.getPrincipal();
            String username = user.getUsername();
            return loadByPerm(username,s);
        }
        return false;
    }

    public boolean loadByPerm(String userName , String prem){
        return true;
    }
}
