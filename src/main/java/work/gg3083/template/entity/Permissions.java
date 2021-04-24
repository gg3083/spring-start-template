package work.gg3083.template.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author Gimi
 * @since 2019-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_permissions")
@ApiModel(value="Permissions对象", description="权限表")
public class Permissions implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "权限名称")
    @TableField("perm_name")
    private String permName;

    @ApiModelProperty(value = "url地址")
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "权限简称例如 system:index:main")
    @TableField("perm_alias")
    private String permAlias;

    @ApiModelProperty(value = "父级Id")
    @TableField("parent_id")
    private Integer parentId;

    @ApiModelProperty(value = "图标")
    @TableField("icon")
    private String icon;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除状态=={\"0\":\"正常\",\"1\":\"禁用\"}")
    @TableField("delete_status")
    @TableLogic
    private Integer deleteStatus;


}
