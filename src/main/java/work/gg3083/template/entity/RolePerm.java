package work.gg3083.template.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 角色权限关联表
 * </p>
 *
 * @author Gimi
 * @since 2019-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_role_perm")
@ApiModel(value="RolePerm对象", description="角色权限关联表")
public class RolePerm implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableField("id")
    private Integer id;

    @ApiModelProperty(value = "角色ID")
    @TableField("role_id")
    private Integer roleId;

    @ApiModelProperty(value = "权限id")
    @TableField("perm_id")
    private Integer permId;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除状态=={\"0\":\"正常\",\"1\":\"禁用\"}")
    @TableField("delete_status")
    @TableLogic
    private Integer deleteStatus;


}
