package work.gg3083.template.filter;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;
import work.gg3083.template.commom.AuthConst;
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
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
//
@Component
//// 定义filterName 和过滤的url
//@WebFilter(filterName = "TokenFilter", urlPatterns = "/*")
@Slf4j
@Configuration
@Order(0)
public class TokenFilter extends BasicAuthenticationFilter {


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


    public TokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("current request url: {}", request.getRequestURI());

        boolean hasCors = request.getMethod().equals(RequestMethod.OPTIONS.name());
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type,Authorization");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        response.setHeader("filter", "doFilterInternal");
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
        if (validationToken(token) == TokenVerifyEnum.PASS) {
            String userName = this.parseToken(token);
            log.debug("解析成功,当前登录用户的ID: {}", userName);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userName, null, new ArrayList<>());
            SecurityContextHolder.getContext().setAuthentication(authentication);
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

    /**
     * Token加密私钥
     */
    @Value("${jwt.base64Secret}")
    private String base64Secret = "6ebe76c9fb411be97b3b0d48b791a7c9";


    private TokenVerifyEnum validationToken(String jsonWebToken) {
        try{
            Claims body = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Secret))
                    .parseClaimsJws(jsonWebToken).getBody();
            logger.debug(body.toString());
            return TokenVerifyEnum.PASS;
        } catch (ExpiredJwtException ex) {
            return TokenVerifyEnum.EXPIRED;
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            return TokenVerifyEnum.INVALID;
        }
    }

    private String parseToken(String auth){
        String userId = null;
        try {
            String token = auth.substring( auth.indexOf(".")+1,auth.lastIndexOf("."));
            String json = new String ( Base64.getDecoder().decode( token) , "UTF-8" );
            JSONObject object = JSONObject.parseObject( json );
            userId = object.get("id").toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return userId;
    }

}
