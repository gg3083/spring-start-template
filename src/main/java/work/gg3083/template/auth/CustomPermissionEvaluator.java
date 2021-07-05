package work.gg3083.template.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;

/***
 *
 * @author Gimi
 * @date 2019/8/13 21:39
 *
 ***/
@Slf4j
@Configuration
public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object perm, Object o1) {
        log.info("开始校验权限 {}", perm);
        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (authorities.contains(new SimpleGrantedAuthority(String.valueOf(perm)))) {
            log.info("hasPermission");
            return true;
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        log.info("hasPermission2");

        return false;
    }
}
