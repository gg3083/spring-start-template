package work.gg3083.template.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Gimi
 * @since 2019-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="角色权限添加", description="角色添加参数")
public class RolePermAddParam implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "角色Id")
    @NotEmpty(message = "角色id不能为空")
    private List<Integer> permIdList;





}
