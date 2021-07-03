package work.gg3083.template.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Gimi
 * @date 2021-07-03 15:28
 */
@Data
public class RolePermVO {
    private Integer roleId;
    private List<Integer> premId;
}
