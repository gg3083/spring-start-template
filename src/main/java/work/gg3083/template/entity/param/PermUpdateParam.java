package work.gg3083.template.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *
 * @author Gimi
 * @since 2019-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="权限修改类", description="权限修改参数")
public class PermUpdateParam implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "权限名称")
    private String permName;

    @ApiModelProperty(value = "url地址")
    private String url;

    @ApiModelProperty(value = "父级Id")
    private Integer parentId;

    @ApiModelProperty(value = "图标")
    private String icon;



}
