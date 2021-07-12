package work.gg3083.template.entity.param;

import lombok.Data;

import java.util.List;

/**
 * @author Gimi
 * @date 2021-07-07 11:11
 */
@Data
public class CodeGeneratePreviewListParam {
    private String tableName;
    private List<String> paramBtn;
    private List<CodeGeneratePreviewParam> paramList;
}
