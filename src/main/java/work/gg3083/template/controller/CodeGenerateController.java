package work.gg3083.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import work.gg3083.template.entity.json.JsonBack;
import work.gg3083.template.entity.param.CodeGeneratePreviewListParam;
import work.gg3083.template.entity.param.CodeGeneratePreviewParam;
import work.gg3083.template.mapper.SystemSqlMapper;
import work.gg3083.template.util.CodeGenerateUtil;

import java.util.HashMap;
import java.util.List;

/**
 * @author Gimi
 * @date 2021-07-07 11:09
 */
@RestController
@RequestMapping("/code-generate")
public class CodeGenerateController {

    @Autowired
    private SystemSqlMapper systemSqlMapper;

    @PostMapping("/table/list/field")
    public JsonBack tableListField() {
        List<String> tableNames = systemSqlMapper.listTable();
        HashMap<String, List<HashMap<String, Object>>> res = new HashMap<>();
        tableNames.forEach(tableName -> {
            res.put(tableName, systemSqlMapper.getFieldByTableName(tableName));
        });
        return JsonBack.buildSuccJson(res);
    }

    @PostMapping("/table/list")
    public JsonBack listTable() {
        final List<String> tableNames = systemSqlMapper.listTable();
        return JsonBack.buildSuccJson(tableNames);
    }

    @PostMapping("/table/getField")
    public JsonBack getFieldByTableName(@RequestBody  List<String> tableNames) {
        HashMap<String, List<HashMap<String, Object>>> res = new HashMap<>();
        tableNames.forEach(tableName -> {
            res.put(tableName, systemSqlMapper.getFieldByTableName(tableName));
        });
        return JsonBack.buildSuccJson(res);
    }

    @PostMapping("/js/preview")
    public JsonBack jsPreView(@RequestBody List<CodeGeneratePreviewListParam> param) {
        HashMap<String, Object> res = new HashMap<>();
        param.forEach(item->{
            final List<CodeGeneratePreviewParam> params = item.getValue();
            final String modelForm = CodeGenerateUtil.createModelForm(params);
            final String tableColumn = CodeGenerateUtil.createTableColumn(params);
            final String addFormColumn = CodeGenerateUtil.createAddFormColumn(params);
            final String updateFormColumn = CodeGenerateUtil.createUpdateFormColumn(params);
            final String searchFormColumn = CodeGenerateUtil.createSearchFormColumn(params);
            StringBuffer sb = new StringBuffer();
            sb.append(modelForm);
            sb.append("\n\n");
            sb.append(tableColumn);
            sb.append("\n\n");
            sb.append(addFormColumn);
            sb.append("\n\n");
            sb.append(updateFormColumn);
            sb.append("\n\n");
            sb.append(searchFormColumn);
            res.put(item.getKey(), sb);
        });

        return JsonBack.buildSuccJson(res);
    }

}
