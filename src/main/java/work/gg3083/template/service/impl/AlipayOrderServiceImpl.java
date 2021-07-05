package work.gg3083.template.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import work.gg3083.template.entity.AlipayOrder;
import work.gg3083.template.entity.Role;
import work.gg3083.template.entity.param.RoleAddParam;
import work.gg3083.template.entity.vo.PageInfo;
import work.gg3083.template.mapper.AlipayOrderMapper;
import work.gg3083.template.service.IAlipayOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Gimi
 * @since 2021-07-05
 */
@Service
public class AlipayOrderServiceImpl extends ServiceImpl<AlipayOrderMapper, AlipayOrder> implements IAlipayOrderService {

    @Override
    public PageInfo<AlipayOrder> list4Page(Integer pageNo, Integer pageSize) {
        Page<AlipayOrder> page = new Page<>(pageNo,pageSize);
        return new PageInfo<>(this.page(page).getRecords());
    }

    @Override
    public Object add(RoleAddParam param) {
        return null;
    }
}
