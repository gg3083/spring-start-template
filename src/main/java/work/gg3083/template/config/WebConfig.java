package work.gg3083.template.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;


@Configuration
@EnableWebMvc
@Slf4j
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .maxAge(3600)
                .allowCredentials(true);
    }

    @Autowired
    private CORSInterceptor corsInterceptor;

    /**
     * 不能直接new 会导致组件未注册
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(corsInterceptor);
    }

    /**
     * 防止@EnableMvc把默认的静态资源路径覆盖了，手动设置的方式
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决静态资源无法访问
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        // 解决swagger无法访问
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        // 解决swagger的js文件无法访问
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

    }


    /**
     * LocalDateTime 解决时间返回给前端会变成数组
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.forEach( it ->{
            if (it instanceof AbstractJackson2HttpMessageConverter){
                ((AbstractJackson2HttpMessageConverter) it).getObjectMapper().setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                ((AbstractJackson2HttpMessageConverter) it).getObjectMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            }
        });
    }

}
