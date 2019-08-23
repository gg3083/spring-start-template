package work.gg3083.template.entity.vo;

import lombok.Data;

/**
 * @author Gimi
 * @date 2019/8/15 11:05
 */
@Data
public class UserVO {
    private Integer id;
    private String loginName;
    private String realName;
    private String password;
    private String roleName;
    private String roleAlias;
    private String token;
}
