package work.gg3083.template.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Gimi
 * @since 2019-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user")
@ApiModel(value="User对象", description="用户表")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("login_name")
    private String loginName;

    @ApiModelProperty(value = "姓名")
    @TableField("real_name")
    private String realName;

    @ApiModelProperty(value = "英文名")
    @TableField("english_name")
    private String englishName;

    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "电话")
    @TableField("telephone")
    private String telephone;

    @ApiModelProperty(value = "工号")
    @TableField("job_no")
    private String jobNo;

    @ApiModelProperty(value = "登录密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "性别=={\"0\":\"男\",\"1\":\"女\"}")
    @TableField("gender")
    private Integer gender;

    @ApiModelProperty(value = "出生日期")
    @TableField("birth_day")
    private LocalDate birthDay;

    @ApiModelProperty(value = "头像")
    @TableField("head_img")
    private String headImg;

    @ApiModelProperty(value = "住址")
    @TableField("address")
    private String address;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除状态=={\"0\":\"正常\",\"1\":\"禁用\"}")
    @TableField("delete_status")
    @TableLogic
    private Integer deleteStatus;


}
