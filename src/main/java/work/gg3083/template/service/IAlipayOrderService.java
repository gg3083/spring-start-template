package work.gg3083.template.service;

import work.gg3083.template.entity.AlipayOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import work.gg3083.template.entity.param.RoleAddParam;
import work.gg3083.template.entity.vo.PageInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gimi
 * @since 2021-07-05
 */
public interface IAlipayOrderService extends IService<AlipayOrder> {

    PageInfo<AlipayOrder> list4Page(Integer pageNo, Integer pageSize);

    Object add(RoleAddParam param);
}
