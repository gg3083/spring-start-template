package work.gg3083.template.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;
import work.gg3083.template.commom.CommonConst;
import work.gg3083.template.component.JwtHelper;
import work.gg3083.template.entity.enums.TokenVerifyEnum;
import work.gg3083.template.entity.json.JsonBack;
import work.gg3083.template.exception.MyExceptionType;
import work.gg3083.template.util.json.JsonUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
// 定义filterName 和过滤的url
@WebFilter(filterName = "TokenFilter", urlPatterns = "/*")
@Slf4j
public class TokenFilter extends OncePerRequestFilter {

    @Autowired
    JwtHelper helper;


    List<String> beginUrls = new ArrayList<>();
    List<String> endUrls = new ArrayList<>();

    {
        //  beginUrls.add("/");
        beginUrls.add("/api");
        beginUrls.add("/open");
//        beginUrls.add("/api/user");
        beginUrls.add("/swagger");
        beginUrls.add("/webjars");
        beginUrls.add("/v2/api-docs");
        beginUrls.add("/login");
        beginUrls.add("/logout");
        endUrls.add(".css");
        endUrls.add(".ico");
        endUrls.add(".js");
        endUrls.add(".jpg");
        endUrls.add(".png");
        endUrls.add("ui");

    }

    private String refreshTokenUrl = "/refreshToken";
    private String apiRefreshTokenUrl = "/auth/refreshToken";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("current request url: {}", request.getRequestURI());

        boolean hasCors = request.getMethod().equals(RequestMethod.OPTIONS.name());
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type,Authorization");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        if (hasCors) {
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
        if (helper.validationToken(token) == TokenVerifyEnum.PASS) {
            String userName = helper.parseToken(token);
            log.debug("解析成功,当前登录用户的ID: {}", userName);
            filterChain.doFilter(request, response);
            return;
        } else if (helper.validationToken(token) == TokenVerifyEnum.EXPIRED) {
            log.debug("当前token已过期");
            if (refreshTokenUrl.equals(request.getRequestURI()) || apiRefreshTokenUrl.equals(request.getRequestURI())) {
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
}
