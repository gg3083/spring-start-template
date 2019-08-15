package work.gg3083.template;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@MapperScan("work.gg3083.template.mapper")
public class QuickStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuickStartApplication.class, args);
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

}
