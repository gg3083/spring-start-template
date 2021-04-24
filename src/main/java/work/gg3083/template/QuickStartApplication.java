package work.gg3083.template;

import io.swagger.annotations.Api;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import work.gg3083.template.entity.json.JsonBack;

@SpringBootApplication
@RestController
@MapperScan("work.gg3083.template.mapper")
@Api(value="主页测试", tags={"主页测试"})
public class QuickStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuickStartApplication.class, args);
    }

    @GetMapping({"/", "/index"})
    public JsonBack index(){
        return JsonBack.buildSuccJson("index");
    }

}
