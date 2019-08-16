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
@RequestMapping("/permissions")
@Api(value="权限控制器", tags={"权限控制器"})
public class PermissionsController {

    @Autowired
    private IPermissionsService permissionsService;

    @PostMapping("getByName")
    public JsonBack getByName(String loginName){
        return JsonBack.buildSuccJson(permissionsService.findPermByLoginName(loginName));
    }

    @GetMapping("list")
    public JsonBack<PageInfo<Permissions>> list(@RequestParam(name = "pageNo",defaultValue = "1") Integer pageNo,
                                                @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize,
                                                String searchKey){
        return JsonBack.buildSuccJson(permissionsService.list4Page(pageNo,pageSize,searchKey));
    }

    @PostMapping("add")
    public JsonBack add(@RequestBody @Validated PermAddParam param){
        return JsonBack.buildSuccJson(permissionsService.add(param));
    }

    @PostMapping("update")
    public JsonBack update(@RequestBody @Validated PermUpdateParam param){
        return JsonBack.buildSuccJson(permissionsService.update(param));
    }

    @GetMapping("get")
    public JsonBack<Permissions> get(@RequestParam Integer id){
        return JsonBack.buildSuccJson(permissionsService.get(id));
    }

    @PostMapping("delete")
    public JsonBack delete(@RequestParam Integer id){
        return JsonBack.buildSuccJson(permissionsService.delete(id));
    }
}

