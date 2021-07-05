package work.gg3083.template.util;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import work.gg3083.template.entity.enums.TokenVerifyEnum;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author Gimi
 * @date 2021-07-05 18:33
 */
public class TokenUtil {


    public static TokenVerifyEnum validationToken(String jsonWebToken, String secret) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                    .parseClaimsJws(jsonWebToken).getBody();
            return TokenVerifyEnum.PASS;
        } catch (ExpiredJwtException ex) {
            return TokenVerifyEnum.EXPIRED;
        } catch (Exception ex) {
            return TokenVerifyEnum.INVALID;
        }
    }

    public static String parseToken(String auth) {
        String userId = null;
        try {
            String token = auth.substring(auth.indexOf(".") + 1, auth.lastIndexOf("."));
            String json = new String(Base64.getDecoder().decode(token), "UTF-8");
            JSONObject object = JSONObject.parseObject(json);
            userId = object.get("id").toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return userId;
    }

}
