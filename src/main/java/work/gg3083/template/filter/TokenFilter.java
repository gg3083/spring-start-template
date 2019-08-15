package work.gg3083.template.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;
import work.gg3083.template.component.JwtHelper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Component
// 定义filterName 和过滤的url
@WebFilter(filterName = "TokenFilter" ,urlPatterns = "/*")
public class TokenFilter extends OncePerRequestFilter {

    @Autowired
    JwtHelper helper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if ( helper.validationToken(token)){
            String name = helper.parseToken(token);
            System.err.println("解析成功 {}"+name);
            filterChain.doFilter(request, response);
            return;
        }
        response.getWriter().write("{\"token\":\"miss\"}");
    }
}
