package work.gg3083.template.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *
 * @author Gimi
 * @since 2019-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="角色修改", description="角色修改参数")
public class RoleUpdateParam implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "ID")
    @NotNull(message = "ID")
    private Integer id;

    @ApiModelProperty(value = "角色名称")
    @NotNull(message = "角色名称不能为空")
    private String roleName;

    @ApiModelProperty(value = "别名")
    @NotNull(message = "角色别名不能为空")
    private String roleAlias;



}
