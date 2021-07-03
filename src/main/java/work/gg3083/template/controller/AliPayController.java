package work.gg3083.template.controller;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Gimi
 * @date 2021-06-09 17:19
 */
public class AliPayController {


    public static void main(String[] args) throws Exception {
        // 1. 设置参数（全局只需设置一次）
        Factory.setOptions(getOptions());
        try {
            // 2. 发起API调用（以创建当面付收款二维码为例）
            AlipayTradePrecreateResponse response = Factory.Payment.FaceToFace()
                    .preCreate("Apple iPhone11 128G", "2234567890", "5799.00");
            // 3. 处理响应或异常
            if (ResponseChecker.success(response)) {
                System.out.println("调用成功:");
                Map<String, Object> stringObjectMap = response.toMap();
                stringObjectMap.forEach((k, v) -> {
                    System.err.println(k + ":" + v);
                });
            } else {
                System.err.println("调用失败，原因：" + response.msg + "，" + response.subMsg);
            }
        } catch (Exception e) {
            System.err.println("调用遭遇异常，原因：" + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }


    private static Config getOptions() {
        Config config = new Config();
        config.protocol = "https";
        config.gatewayHost = "openapi.alipaydev.com";
        config.signType = "RSA2";

        config.appId = "2016091000479926";

        // 为避免私钥随源码泄露，推荐从文件中读取私钥字符串而不是写入源码中
        config.merchantPrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCSKdYMX0e7zdUsB9r4rhWJQnSa1wdZWJl0OAa8XmgE1bho/lE3qU1MK0Wt7V8IW8ifvUgYG2cBJ+XDCONAYIS2hs1EyBtokD75jo1BwPDKQmDlNXNvy5zEnkYxUmYncsN4M2BVbj24AgvM5Yq0RdeKn3mshQiYbCKeGidLftN5RFwqg7BXHATOM2Tglk6t1k1WTYqLgr+Z5p5f3iRRgT77s5MLzztO4imL+7RXnRti6bkpP8YpovCKlPrZLtWBkGp11F/QNSq548Ro9ot6TcHkBpcF0PiLB16aVqWZ/E9jfr/HY6OgYnO+Y4LPivBbcCcNU8mG7QzgO+yVsf1af2udAgMBAAECggEAFA1XR0U/Ex3O9AZz3RAM9lK+qot7f59vFgmlquwMuzUS4pyZSdiZOSzql0zsb7of0QJnxJ7lx7vQgqxSeP+E1YGy4Y/Oas73RMdwy6eLoUPwBSILHzhA3pyUJqoWR60v8eqiebXxE7B8K/UFzWuTgXoVeDycDEUxjp0p7OqOWqF4HxppothilP8mzQ33SN4Bs5WaAn7Rvl9dcnrX9v/kT5PTtacAo/fxT2qcxiRq/L8Iz/dt8Y782Xr4/JbzK6lnCqHvlhEdWCeAv14ySmOFcAWKl0PYVctFgD+9z+O+z6Ytt6TPBukO7d7Oy2vgxDztuxvV6ZZ3G7/lhWx3OfzopQKBgQDKE0jQIgCwoslGQykg5jQKkukriWbLijFQBIhrRGSwuMPc9x+JLmFzREovk4aFHFmUIP94ukt1jBpr8fi4dvuNjKpw9SKeX7NCOTnkRGM90lD9aMxbF9p3fjaCKuLMBYrlZl3+xlAL8xhEbfAKq6SSuNWd2q1VtqeLr9wuJqw+JwKBgQC5KvJORsJlTKZnC09i8Ymk3TVYbaC3PNz5V0/YQxJhzUhIRozzVK1SN0uUhLsDsUAershKrGO9m3vE7cMPq40R3pKQlPPB36zEY8Ivx03c46QLFVxSsLdxjxBVjovecM1KT6N6jJDxk5u/A/BA8GixoKuVlH3haP0YHKVbwqQmmwKBgQC5itFtgfseNKoYoKwP8C9SkigUeJnxQ9cZgL/9thO6DDyTFDlZjUOfQdHYM13ttOUvVMkeAfgtW1IbUVctkNpS0xjglMSPpJGEueTFCmIRLp58v4/8CD/pPfMwu834xRIOplqiB1cSUcgzdLpWIdn/ekVyJorHS+o1IWlK2umGOwKBgQCNJHpMRk15b8LLeuiJT/mObHt0KyQ9aCw791zKSN3jJNn7FaPs+teeer2muMHgn6RzfuD3Yo4ogjMGugFcmXRPcaugf5mKZ1fG3w+u4amtPOvW8vJLqZ469kUKIjzlUnB5O1kuWaVGLzChmnvw1A/JjeIcg3Ksdh2t2nAFEMmVDwKBgBQgevRqAlu2Xx6wP+/22wEirbUF0PhXvMLsQyOQLsQCsv+gW8s9JRfKOV0EuAcyFxP3yCKrepY3YZKk/VySt16nxE8uz28Rf/K5g3sxpDPBC+kMYHH9DZ8hkFQOC/Ui+nqoBdPjS354dHpIRzAWfIw2JT6sw1hxZCOGqGF0xAPE";

        //注：证书文件路径支持设置为文件系统中的路径或CLASS_PATH中的路径，优先从文件系统中加载，加载失败后会继续尝试从CLASS_PATH中加载
        config.merchantCertPath = "lib/appCertPublicKey_2016091000479926.crt";
        config.alipayCertPath = "lib/alipayCertPublicKey_RSA2.crt";
        config.alipayRootCertPath = "lib/alipayRootCert.crt";

        //注：如果采用非证书模式，则无需赋值上面的三个证书路径，改为赋值如下的支付宝公钥字符串即可
//         config.alipayPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB";

        //可设置异步通知接收服务地址（可选）
        config.notifyUrl = "http://gg.3083.work/work/callback";

        //可设置AES密钥，调用AES加解密相关接口时需要（可选）
//        config.encryptKey = "<-- 请填写您的AES密钥，例如：aa4BtZ4tspm2wnXLb1ThQA== -->";

        return config;
    }
}
