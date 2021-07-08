package work.gg3083.template.entity.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Gimi
 * @date 2021-07-07 11:11
 */
@Data
public class CodeGeneratePreviewParam {
    @JsonProperty("Field")
    private String Field;
    @JsonProperty("Type")
    private String Type;
    @JsonProperty("Comment")
    private String Comment;
    private Boolean isAdd;
    private Boolean isSearch;
    private Boolean isUpdate;
    private Boolean isShow;
}
