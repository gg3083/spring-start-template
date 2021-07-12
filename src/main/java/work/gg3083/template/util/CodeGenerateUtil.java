package work.gg3083.template.util;

import org.springframework.util.CollectionUtils;
import work.gg3083.template.entity.param.CodeGeneratePreviewParam;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @author Gimi
 * @date 2021-07-07 11:14
 */
public class CodeGenerateUtil {

    public static String createModelForm(List<CodeGeneratePreviewParam> parmList) {
        String modelForm = "export const modelForm = { %s }";
        StringBuilder sb = new StringBuilder();
        parmList.forEach(item -> {
            String fieldModelForm = createFieldModelForm(item.getField(), item.getComment(), item.getType());
            sb.append(fieldModelForm);
        });
        return String.format(modelForm, sb);
    }

    public static String createFieldModelForm(String key, String label, String type) {
        String template = "%s: { label: '%s', value: '', type: %s },";
        if (type.contains("int")) {
            type = "Number";
        } else {
            type = "\'\'";
        }
        return String.format(template, key, label, type);
    }

    public static String createTableColumn(List<String> paramBtn, List<CodeGeneratePreviewParam> parmList) {
        String tableColumn = "export const tableColumn = [ %s {prop:'operate',label:'操作',width:'400px',btnData:[%s],},]";
        StringBuilder sb = new StringBuilder();
        parmList.forEach(item -> {
            if (item.getIsShow()) {
                String fieldModelForm = createFieldTableColumn(item.getField(), item.getComment());
                sb.append(fieldModelForm);
            }
        });
        StringBuilder sub = new StringBuilder();
        if (!CollectionUtils.isEmpty(paramBtn)) {
            for (String btn : paramBtn) {
                String btnModelForm = createBtnTableColumn(btn);
                if (btnModelForm != null) {
                    sub.append(btnModelForm);
                }
            }

        }
        return String.format(tableColumn, sb, sub);
    }

    public static String createFieldTableColumn(String key, String label) {
        String template = "{ prop: '%s',label: '%s'},";
        return String.format(template, key, label);
    }

    public static String createBtnTableColumn(String key) {
        String template = " { btnKey: '%s', btnText: '%s', btnType: '%s', round: true },";
        if (!key.contains("Btn")) {
            return null;
        }
        String btnText = "";
        String btnType = "";
        switch (key) {
            case "viewBtn":
                btnText = "查看";
                btnType = "info";
                break;
            case "editBtn":
                btnText = "编辑";
                btnType = "primary";
                break;
            case "delBtn":
                btnText = "删除";
                btnType = "danger";
                break;
        }
        return String.format(template, key, btnText, btnType);
    }

    public static String createAddFormColumn(List<CodeGeneratePreviewParam> parmList) {
        String tableColumn = "export const addModelFormColumn = [ %s ]";
        StringBuilder sb = new StringBuilder();
        parmList.forEach(item -> {
            if (item.getIsAdd()) {
                String fieldModelForm = createFieldFormColumn(item.getField(), item.getComment());
                sb.append(fieldModelForm);
            }
        });
        return String.format(tableColumn, sb);
    }

    public static String createUpdateFormColumn(List<CodeGeneratePreviewParam> parmList) {
        String tableColumn = "export const editModelFormColumn = [ %s ]";
        StringBuilder sb = new StringBuilder();
        parmList.forEach(item -> {
            if (item.getIsUpdate()) {
                String fieldModelForm = createFieldFormColumn(item.getField(), item.getComment());
                sb.append(fieldModelForm);
            }
        });
        return String.format(tableColumn, sb);
    }

    public static String createSearchFormColumn(List<CodeGeneratePreviewParam> parmList) {
        String tableColumn = "export const searchFormColumn = [ %s ]";
        StringBuilder sb = new StringBuilder();
        parmList.forEach(item -> {
            if (item.getIsSearch()) {
                String fieldModelForm = createFieldFormColumn(item.getField(), item.getComment());
                sb.append(fieldModelForm);
            }
        });
        return String.format(tableColumn, sb);
    }

    public static String createFieldFormColumn(String key, String label) {
        String template = "{ prop: '%s',label: '%s', value: '',},";
        return String.format(template, key, label);
    }

    public static String createOperateBtn(List<String> paramBtn) {
        if (CollectionUtils.isEmpty(paramBtn)) {
            return "";
        }
        String operateBtn = "export const operateBtn = [ %s ]";
        StringBuilder sb = new StringBuilder();
        for (String btn : paramBtn) {
            if (Objects.equals(btn, "_getPageTab1")) {
                sb.append("{label:'搜索',type:'info',size:'mini',clickFunc:'_getPageTab1',args:[1,10],},");
            } else if (Objects.equals(btn, "showAddModel")) {
                sb.append("{label:'添加',type:'primary',size:'mini',clickFunc:'showAddModel',},");
            }
        }
        return String.format(operateBtn, sb);

    }
}
