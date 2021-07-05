package work.gg3083.template.controller;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import work.gg3083.template.entity.json.JsonBack;
import work.gg3083.template.entity.param.RoleAddParam;
import work.gg3083.template.entity.param.RoleUpdateParam;
import work.gg3083.template.service.IAlipayOrderService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Gimi
 * @since 2021-07-05
 */
@RestController
@RequestMapping("/alipay-order")
@Slf4j
public class AlipayOrderController {

    @Autowired
    private IAlipayOrderService alipayOrderService;


    @Data
    static
    class QueryParams{
        private String outTradeNo;
        private String tradeStatus;
    }

    @GetMapping("list")
    public JsonBack list(@RequestParam(name = "pageNo",defaultValue = "1") Integer pageNo,
                         @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize,
                         QueryParams queryParams){
        return JsonBack.buildSuccJson(alipayOrderService.list4Page(pageNo,pageSize));
    }

    @PostMapping("add")
    @PreAuthorize("hasPermission('s:order:alipay_order:btn_add:btn','')")
    public JsonBack add(){
        log.info("开始添加:");
        return JsonBack.buildSuccJson();
    }

    @PostMapping("/update/{id}")
    public JsonBack update(@PathVariable Integer id, @RequestBody @Validated RoleUpdateParam param){
//        return JsonBack.buildSuccJson(alipayOrderService.update(id, param));
        return null;
    }

    @GetMapping("get")
    public JsonBack get(@RequestParam Integer id){
        return JsonBack.buildSuccJson(alipayOrderService.getById(id));
    }

    @PostMapping("/delete/{id}")
    public JsonBack delete(@PathVariable Integer id){
        return JsonBack.buildSuccJson(alipayOrderService.removeById(id));
    }
}

