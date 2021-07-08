package work.gg3083.template.util;

import work.gg3083.template.entity.param.CodeGeneratePreviewParam;

import java.util.List;

/**
 * @author Gimi
 * @date 2021-07-07 11:14
 */
public class CodeGenerateUtil {

    public static String createModelForm(List<CodeGeneratePreviewParam> parmList) {
        String modelForm = "export const modelForm = { %s }";
        StringBuilder sb = new StringBuilder();
        parmList.forEach(item -> {
            final String fieldModelForm = createFieldModelForm(item.getField(), item.getComment(), item.getType());
            sb.append(fieldModelForm);
        });
        return String.format(modelForm, sb);
    }

    public static String createFieldModelForm(String key, String label, String type) {
        String template = "%s: { label: '%s', value: '', type: %s },";
        if (type.contains("int")){
            type = "Number";
        }else {
            type = "\'\'";
        }
        return String.format(template, key, label, type);
    }

    public static String createTableColumn(List<CodeGeneratePreviewParam> parmList) {
        String tableColumn = "export const tableColumn = [ %s ]";
        StringBuilder sb = new StringBuilder();
        parmList.forEach(item -> {
            if (item.getIsShow()) {
                final String fieldModelForm = createFieldTableColumn(item.getField(), item.getComment());
                sb.append(fieldModelForm);
            }
        });
        return String.format(tableColumn, sb);
    }

    public static String createFieldTableColumn(String key, String label) {
        String template = "{ prop: '%s',label: '%s'},";
        return String.format(template, key, label);
    }

    public static String createAddFormColumn(List<CodeGeneratePreviewParam> parmList) {
        String tableColumn = "export const addModelFormColumn = [ %s ]";
        StringBuilder sb = new StringBuilder();
        parmList.forEach(item -> {
            if (item.getIsAdd()) {
                final String fieldModelForm = createFieldFormColumn(item.getField(), item.getComment());
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
                final String fieldModelForm = createFieldFormColumn(item.getField(), item.getComment());
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
                final String fieldModelForm = createFieldFormColumn(item.getField(), item.getComment());
                sb.append(fieldModelForm);
            }
        });
        return String.format(tableColumn, sb);
    }

    public static String createFieldFormColumn(String key, String label) {
        String template = "{ prop: '%s',label: '%s', value: '',},";
        return String.format(template, key, label);
    }

}
