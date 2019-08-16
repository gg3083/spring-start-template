package work.gg3083.template.entity.param;

import com.baomidou.mybatisplus.annotation.TableName;
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
@ApiModel(value="角色添加", description="角色添加参数")
public class RoleAddParam implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "角色名称")
    @NotNull(message = "角色名称不能为空")
    private String roleName;

    @ApiModelProperty(value = "别名")
    @NotNull(message = "角色别名不能为空")
    private String roleAlias;



}
