package work.gg3083.template.util;


import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.pkcs.RSAPrivateKeyStructure;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


public class RsaUtil {

    public static final String RSA_ALGORITHM = "RSA";

    public static final String SIGN_ALGORITHMS = "SHA256withRSA";

    public static final String CHARSET_UTF8 = "UTF-8";

    /**
     * RSA签名
     * @param content 待签名数据
     * @param privateKey 商户私钥
     * @return 签名值
     */
    public static String sign(String content, String privateKey) {
        try {
            byte[] bytes = Base64.getDecoder().decode(privateKey);
            PrivateKey privateKey1 = getPrivateKeyPKCS1Key(bytes);
            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initSign(privateKey1);
            signature.update( content.getBytes(CHARSET_UTF8));
            return Base64.getEncoder().encodeToString(signature.sign());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取PKCS1格式的私钥
     * @param privateKeyBytes
     * @return
     * @throws Exception
     */
    private static PrivateKey getPrivateKeyPKCS1Key(byte[] privateKeyBytes) throws Exception{
        RSAPrivateKeyStructure asn1PrivateKey = new RSAPrivateKeyStructure((ASN1Sequence) ASN1Sequence.fromByteArray(privateKeyBytes));
        RSAPrivateKeySpec rsaPrivateKeySpec = new RSAPrivateKeySpec(asn1PrivateKey.getModulus(), asn1PrivateKey.getPrivateExponent());
        KeyFactory keyFactory=  KeyFactory.getInstance(RSA_ALGORITHM);
        return keyFactory.generatePrivate(rsaPrivateKeySpec);
    }

    /**
     * RSA验签名检查
     * @param content 待签名数据
     * @param sign 签名值
     * @param publicKey 分配给开发商的公钥
     * @return 布尔值
     */
    public static boolean doCheck(String content, String sign, String publicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            byte[] encodedKey = Base64.getDecoder().decode(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initVerify(pubKey);
            signature.update( content.getBytes("UTF-8") );
            return signature.verify( Base64.getDecoder().decode(sign) );
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
