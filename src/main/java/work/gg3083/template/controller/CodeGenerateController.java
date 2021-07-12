package work.gg3083.template.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import work.gg3083.template.entity.json.JsonBack;
import work.gg3083.template.entity.param.CodeGeneratePreviewListParam;
import work.gg3083.template.entity.param.CodeGeneratePreviewParam;
import work.gg3083.template.mapper.SystemSqlMapper;
import work.gg3083.template.util.CodeGenerateUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

/**
 * @author Gimi
 * @date 2021-07-07 11:09
 */
@RestController
@RequestMapping("/code-generate")
@Slf4j
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
            final List<CodeGeneratePreviewParam> params = item.getParamList();
            final String modelForm = CodeGenerateUtil.createModelForm(params); //实体类
            final String tableColumn = CodeGenerateUtil.createTableColumn(item.getParamBtn(), params); //表格显示类
            final String addFormColumn = CodeGenerateUtil.createAddFormColumn(params); //添加
            final String updateFormColumn = CodeGenerateUtil.createUpdateFormColumn(params);//修改
            final String searchFormColumn = CodeGenerateUtil.createSearchFormColumn(params); //搜索框
            final String operateBtn = CodeGenerateUtil.createOperateBtn(item.getParamBtn()); //搜索栏边的按钮
            StringBuffer sb = new StringBuffer();
            sb.append(modelForm);
            sb.append("\n\n");
            sb.append(operateBtn);
            sb.append("\n\n");
            sb.append(tableColumn);
            sb.append("\n\n");
            sb.append(searchFormColumn);
            sb.append("\n\n");
            sb.append(addFormColumn);
            sb.append("\n\n");
            sb.append(updateFormColumn);
            sb.append("\n\n");
            res.put(item.getTableName(), sb);
        });
        return JsonBack.buildSuccJson(res);
    }

    @PostMapping("/js/export")
    public void export(@RequestBody List<CodeGeneratePreviewListParam> param,  HttpServletResponse response) {
        HashMap<String, Object> res = new HashMap<>();

        param.forEach(item->{
            final List<CodeGeneratePreviewParam> params = item.getParamList();
            final String modelForm = CodeGenerateUtil.createModelForm(params); //实体类
            final String tableColumn = CodeGenerateUtil.createTableColumn(item.getParamBtn(), params); //表格显示类
            final String addFormColumn = CodeGenerateUtil.createAddFormColumn(params); //添加
            final String updateFormColumn = CodeGenerateUtil.createUpdateFormColumn(params);//修改
            final String searchFormColumn = CodeGenerateUtil.createSearchFormColumn(params); //搜索框
            final String operateBtn = CodeGenerateUtil.createOperateBtn(item.getParamBtn()); //搜索栏边的按钮
            StringBuffer sb = new StringBuffer();
            sb.append(modelForm);
            sb.append("\n\n");
            sb.append(operateBtn);
            sb.append("\n\n");
            sb.append(tableColumn);
            sb.append("\n\n");
            sb.append(searchFormColumn);
            sb.append("\n\n");
            sb.append(addFormColumn);
            sb.append("\n\n");
            sb.append(updateFormColumn);
            sb.append("\n\n");
            res.put(item.getTableName(), sb);
        });

        response.setContentType("application/octet-stream");
        String filepath = "export.js";
        try{
            String fileName= URLDecoder.decode(filepath,"utf-8");
            response.addHeader("Content-Disposition","attachment;"+ "filename=\"" + URLEncoder.encode(fileName, "utf-8") + "\"");

            BufferedInputStream input = new BufferedInputStream(new ByteArrayInputStream(res.toString().getBytes(StandardCharsets.UTF_8)));
            byte[] buffer = new byte[input.available()];
            input.read(buffer);
            input.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=export.js");
            response.addHeader("Content-Length", "" + res.toString().length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }




}
