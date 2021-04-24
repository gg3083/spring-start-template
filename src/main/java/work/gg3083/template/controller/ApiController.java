package work.gg3083.template.controller;

/**
 * @author Gimi
 * @date 2021-04-24 17:10
 */

import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;
import work.gg3083.template.entity.json.JsonBack;

/**
 * @author Gimi
 * @since 2021-04-15
 */
@RestController
@RequestMapping("/api")
@Api(value="API ", tags={"Api"})
public class ApiController {

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public JsonBack all(){
        return JsonBack.buildSuccJson("All");
    }
}
