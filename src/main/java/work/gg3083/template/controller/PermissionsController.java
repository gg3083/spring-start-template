package work.gg3083.template.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import work.gg3083.template.entity.json.JsonBack;
import work.gg3083.template.service.IPermissionsService;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author Gimi
 * @since 2019-08-15
 */
@RestController
@RequestMapping("/permissions")
public class PermissionsController {

    @Autowired
    private IPermissionsService permissionsService;

    @PostMapping("getByName")
    public JsonBack getByName(String loginName){
        return JsonBack.buildSuccJson(permissionsService.findPermByLoginName(loginName));
    }

}

