package work.gg3083.template.controller;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import work.gg3083.template.entity.json.JsonBack;
import work.gg3083.template.entity.param.RolePermAddParam;
import work.gg3083.template.service.IRolePermService;

/**
 * <p>
 * 角色权限关联表 前端控制器
 * </p>
 *
 * @author Gimi
 * @since 2019-08-15
 */
@RestController
@RequestMapping("/role-perm")
@Api(value="权限 ", tags={"用户相关"})
public class RolePermController {

    @Autowired
    private IRolePermService rolePermService;

    @PostMapping("/save/{roleId}")
    public JsonBack save(@PathVariable Integer roleId, @RequestBody @Validated RolePermAddParam param){
        return JsonBack.buildSuccJson(rolePermService.save(param, roleId));
    }

    @GetMapping("/select/{roleId}")
    public JsonBack select(@PathVariable Integer roleId){
        return JsonBack.buildSuccJson(rolePermService.select(roleId));
    }

    @GetMapping("/selectAll")
    public JsonBack selectAll(){
        return JsonBack.buildSuccJson(rolePermService.selectAll());
    }

}

