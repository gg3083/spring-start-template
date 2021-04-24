package work.gg3083.template.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import work.gg3083.template.entity.json.JsonBack;

/**
 * @author Gimi
 * @date 2019/8/20 9:26
 */
//@RestController
//@RequestMapping("/api")
public class WechatUtil {

    @Autowired
    RestTemplate restTemplate;

    @ResponseBody
    @GetMapping(value = "/getOpenId")
    public JsonBack decodeUserInfo(String js_code) {

        //登录凭证不能为空
        if (StringUtils.isEmpty(js_code)) {
            return JsonBack.buildErrorJson("code 不能为空");
        }
        //小程序唯一标识   (在微信小程序管理后台获取)
        String wxspAppid = "wxae2560d59590027d";
        //小程序的 app secret (在微信小程序管理后台获取)
        String wxspSecret = "4f5259949e3675ba45478dd2788d116f";
        //授权（必填）
        String grant_type = "authorization_code";


        //////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid ////////////////
        //请求参数
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + js_code + "&grant_type=" + grant_type;
        //发送请求
//        Object sr = restTemplate.getForObject("https://api.weixin.qq.com/sns/jscode2session", Object.class,params);
        String sr = HttpRequestUtil.get("https://api.weixin.qq.com/sns/jscode2session?"+params);
        System.err.println(sr);
        //解析相应内容（转换成json对象）
        try {
            JSONObject json = JSONObject.parseObject(sr.toString());
            //获取会话密钥（session_key）
            String session_key = json.get("session_key").toString();
            //用户的唯一标识（openid）
            String openid = (String) json.get("openid");
        }catch (Exception e){
            JsonBack.buildErrorJson("");
        }


        return JsonBack.buildSuccJson(sr);
    }

}
