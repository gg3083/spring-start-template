package work.gg3083.template.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Gimi
 * @since 2019-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="用户添加类", description="用户添加参数")
public class UserAddParam implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "登录名")
    @NotNull(message = "登录名不能为空")
    private String loginName;

    @ApiModelProperty(value = "姓名")
    private String realName;

    @ApiModelProperty(value = "英文名")
    private String englishName;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "电话")
    private String telephone;

    @ApiModelProperty(value = "工号")
    private String jobNo;

    @ApiModelProperty(value = "登录密码")
    @NotNull(message = "登录密码不能为空")
    private String password;

    @ApiModelProperty(value = "性别=={\"0\":\"男\",\"1\":\"女\"}")
    private Integer gender;

    @ApiModelProperty(value = "出生日期")
    private LocalDate birthDay;

    @ApiModelProperty(value = "头像")
    private String headImg;

    @ApiModelProperty(value = "住址")
    private String address;




}
