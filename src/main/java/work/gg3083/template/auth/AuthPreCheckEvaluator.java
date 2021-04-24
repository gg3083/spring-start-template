package work.gg3083.template.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface AuthPreCheckEvaluator {

    boolean check(UsernamePasswordAuthenticationToken s);

}
