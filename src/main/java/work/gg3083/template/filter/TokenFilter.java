package work.gg3083.template.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import work.gg3083.template.cache.ICache;
import work.gg3083.template.commom.AuthConst;
import work.gg3083.template.commom.CommonConst;
import work.gg3083.template.config.SpringContextUtil;
import work.gg3083.template.entity.enums.TokenVerifyEnum;
import work.gg3083.template.entity.json.JsonBack;
import work.gg3083.template.exception.MyException;
import work.gg3083.template.exception.MyExceptionType;
import work.gg3083.template.util.TokenUtil;
import work.gg3083.template.util.json.JsonUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

//
@Component
//// 定义filterName 和过滤的url
//@WebFilter(filterName = "TokenFilter", urlPatterns = "/*")
@Slf4j
@Configuration
@Order(0)
public class TokenFilter extends BasicAuthenticationFilter {


    /**
     * Token加密私钥
     */
    @Value("${jwt.base64Secret}")
    private String base64Secret = "6ebe76c9fb411be97b3b0d48b791a7c9";

    List<String> beginUrls = new ArrayList<>();
    List<String> endUrls = new ArrayList<>();


    {
//        beginUrls.add("/");
        beginUrls.add("/work/swagger");
        beginUrls.add("/work/webjars");
        beginUrls.add("/work/v2/api-docs");
        beginUrls.add("/work/user/login");
        beginUrls.add("/work/user/logout");
        beginUrls.add("/work/user/register");
        endUrls.add(".css");
        endUrls.add(".ico");
        endUrls.add(".js");
        endUrls.add(".jpg");
        endUrls.add(".png");
        endUrls.add("ui");

    }

    private static final String FILTER_APPLIED = "__spring_security_myAuthenticationTokenGenericFilter_filterApplied";


    public TokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean isCors = Objects.equals(request.getMethod(), RequestMethod.OPTIONS.name());
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type,Authorization");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        response.setHeader("filter", "doFilterInternal");
        if (isCors) {
            filterChain.doFilter(request, response);
            return;
        }
        if (checkUrl(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = request.getHeader(CommonConst.AUTH_HEADER);
        if (StringUtils.isEmpty(token)) {
            response.getWriter().write(JsonUtil.beanToJson(JsonBack.buildErrorJson(MyExceptionType.TOKEN_EMPTY)));
            return;
        }
        if (request.getAttribute(FILTER_APPLIED) != null) {
            filterChain.doFilter(request, response);
            return;
        }
        log.info("current request url: {} {}", request.getRequestURI(), request.getMethod());
        request.setAttribute(FILTER_APPLIED, Boolean.TRUE);
        if (validationToken(token) == TokenVerifyEnum.PASS) {
            String userId = TokenUtil.parseToken(token);
            log.debug("解析成功,当前登录用户的ID: {}", userId);
            if (!this.setAuth(userId)) {
                response.getWriter().write(JsonUtil.beanToJson(JsonBack.buildErrorJson(MyExceptionType.NOT_SESSION)));
                return;
            }
            filterChain.doFilter(request, response);
            return;
        } else if (validationToken(token) == TokenVerifyEnum.EXPIRED) {
            log.debug("当前token已过期");
            if (AuthConst.REFRESH_TOKEN_URL_PRE.equals(request.getRequestURI())) {
                filterChain.doFilter(request, response);
            } else {
                response.getWriter().write(JsonUtil.beanToJson(JsonBack.buildErrorJson(MyExceptionType.TOKEN_EXPIRED)));
            }
            return;
        }
        response.getWriter().write(JsonUtil.beanToJson(JsonBack.buildErrorJson(MyExceptionType.TOKEN_FAIL)));
    }


    private boolean checkUrl(String url) {
        for (String s : beginUrls) {
            if (url.startsWith(s)) {
                return true;
            }
        }
        for (String endUrl : endUrls) {
            if (url.endsWith(endUrl)) {
                return true;
            }
        }
        return false;
    }


    private TokenVerifyEnum validationToken(String jsonWebToken) {
        return TokenUtil.validationToken(jsonWebToken, base64Secret);
    }



    private boolean setAuth(String userId){
        ICache<String> cache = SpringContextUtil.getBean(ICache.class);
        List<String> auths = cache.get(userId);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if (!CollectionUtils.isEmpty(auths)){
            auths.forEach(item->{
                authorities.add(new SimpleGrantedAuthority(item));
            });
        }else {
            return false;
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userId, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return true;
    }

}
