package work.gg3083.template.component;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import work.gg3083.template.commom.CommonConst;
import work.gg3083.template.entity.User;
import work.gg3083.template.entity.enums.TokenVerifyEnum;
import work.gg3083.template.entity.vo.UserVO;
import work.gg3083.template.service.IUserService;
import work.gg3083.template.util.TokenUtil;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Gimi
 * @date 2020-10-19 17:30
 */
@Component
@Order(-1)
public class JwtHelper {

    private  static final Logger logger = LoggerFactory.getLogger(JwtHelper.class);

    @Autowired
    private IUserService userService;

    /**
     * Token加密私钥
     */
    @Value("${jwt.base64Secret}")
    private String base64Secret;

    /**
     * Token过期时间
     */
    @Value("${jwt.expiresTime}")
    private long expires;

    public JwtHelper(){
    }

    public String createToken(HashMap<String,Object> map) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        logger.debug("base64Secret:{},expires:{}",base64Secret,expires);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Secret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT");
        for (Map.Entry<String,Object> entry : map.entrySet()){
            builder = builder.claim(entry.getKey(),entry.getValue());
        }
        builder = builder.claim("signTime",nowMillis)
                .claim("expires",expires * 1000)
                .signWith(signatureAlgorithm,signingKey);
        if (expires >= 0) {
            long expMillis = nowMillis + expires*1000;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }
        return builder.compact();
    }


    public String buildToken(Object id, Object loginName, Object role){
        HashMap<String, Object> map = new HashMap();
        map.put(CommonConst.ID, id);
        map.put(CommonConst.LOGIN_NAME, loginName);
        map.put(CommonConst.ROLE_ID, role);
        return this.createToken(map);
    }

    public String generateToken(UserVO user){
        if (user == null){
            return null;
        }
        return buildToken(user.getId(), user.getLoginName(), user.getRoleId());
    }

    public TokenVerifyEnum validationToken(String jsonWebToken) {
        return TokenUtil.validationToken(jsonWebToken, base64Secret);
    }

    public User parseTokenToModel(String auth){
        String userId = null;
        try {
            String token = auth.substring( auth.indexOf(".")+1,auth.lastIndexOf("."));
            String json = new String ( Base64.getDecoder().decode( token) , "UTF-8" );
            JSONObject object = JSONObject.parseObject( json );
            userId = object.get("id").toString();
            return userService.getById(userId);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
