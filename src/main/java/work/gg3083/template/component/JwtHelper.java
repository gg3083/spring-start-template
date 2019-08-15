package work.gg3083.template.component;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtHelper {

    private  static final Logger logger = LoggerFactory.getLogger(JwtHelper.class);

    final String base64Secret = "123123123123123123123123";
    final long expires = 60*60;

    public String createToken(HashMap<String,String> map) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        logger.debug("base64Secret:{},expires:{}",base64Secret,expires);

        //generate the signingKey
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Secret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //add the parameter for jwt
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT");



        for (Map.Entry<String,String> entry : map.entrySet()){
            builder = builder.claim(entry.getKey(),entry.getValue());
        }

        builder = builder.claim("signTime",nowMillis)
                .claim("expires",expires * 1000)
                .signWith(signatureAlgorithm,signingKey);

        //add the expires
        if (expires >= 0) {
            long expMillis = nowMillis + expires*1000;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }
        // build Token
        String token = builder.compact();
        return token;
    }

    public boolean validationToken(String jsonWebToken) {
        try {
            Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(base64Secret))
                .parseClaimsJws(jsonWebToken).getBody();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public String parseToken( String auth){
        String userName = null;
        try {
            String token = auth.substring( auth.indexOf(".")+1,auth.lastIndexOf("."));
            String json = new String ( Base64.getDecoder().decode( token) , "UTF-8" );
            JSONObject object = JSONObject.parseObject( json );
            userName = object.get("name").toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return userName;

    }
}
