package work.gg3083.template.controller;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import work.gg3083.template.entity.Permissions;
import work.gg3083.template.entity.json.JsonBack;
import work.gg3083.template.entity.param.PermAddParam;
import work.gg3083.template.entity.param.PermUpdateParam;
import work.gg3083.template.entity.vo.PageInfo;
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
@RequestMapping("/permission")
@Api(value="权限", tags={"用户相关"})
public class PermissionsController {

    @Autowired
    private IPermissionsService permissionsService;

    @PostMapping("getByName")
    public JsonBack getByName(String loginName){
        return JsonBack.buildSuccJson(permissionsService.findPermByLoginName(loginName));
    }

    @GetMapping("list")
    public JsonBack list(@RequestParam(name = "pageNo",defaultValue = "1") Integer pageNo,
                                                @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize,
                                                String searchKey){
        return JsonBack.buildSuccJson(permissionsService.list(pageNo,pageSize,searchKey));
    }

    @PostMapping("add")
    public JsonBack add(@RequestBody @Validated PermAddParam param){
        return JsonBack.buildSuccJson(permissionsService.add(param));
    }

    @PostMapping("/update/{id}")
    public JsonBack update(@PathVariable Integer id, @RequestBody @Validated PermUpdateParam param){
        return JsonBack.buildSuccJson(permissionsService.update(id, param));
    }

    @GetMapping("get")
    public JsonBack get(@RequestParam Integer id){
        return JsonBack.buildSuccJson(permissionsService.get(id));
    }

    @PostMapping("/delete/{id}")
    public JsonBack delete(@PathVariable Integer id){
        return JsonBack.buildSuccJson(permissionsService.delete(id));
    }
}

