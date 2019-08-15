package work.gg3083.template.auth;

import org.springframework.security.core.Authentication;

import java.io.Serializable;

/***
 *
 * @author Gimi
 * @date 2019/8/13 21:39
 *
 ***/
public class CustomPermissionEvaluator implements org.springframework.security.access.PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object o, Object o1) {
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
