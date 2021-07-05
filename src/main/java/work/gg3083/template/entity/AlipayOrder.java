package work.gg3083.template.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Gimi
 * @since 2021-07-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_alipay_order")
@ApiModel(value="AlipayOrder对象", description="")
public class AlipayOrder implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField("delete_status")
    @TableLogic
    private Integer deleteStatus;

    @TableField("seller_email")
    private String sellerEmail;

    @TableField("invoice_amount")
    private String invoiceAmount;

    @TableField("notify_time")
    private String notifyTime;

    @TableField("buyer_logon_id")
    private String buyerLogonId;

    @TableField("total_amount")
    private String totalAmount;

    @TableField("trade_no")
    private String tradeNo;

    @TableField("out_trade_no")
    private String outTradeNo;

    @TableField("notify_id")
    private String notifyId;

    @TableField("trade_status")
    private String tradeStatus;

    @TableField("order_real_status")
    private Integer orderRealStatus;

    @TableField("receipt_amount")
    private String receiptAmount;

    @TableField("buyer_pay_amount")
    private String buyerPayAmount;

    @TableField("gmt_create")
    private String gmtCreate;

    @TableField("gmt_payment")
    private String gmtPayment;

    @TableField("subject")
    private String subject;

    @TableField("seller_id")
    private String sellerId;

    @TableField("qr_code")
    private String qrCode;

    @TableField("user_name")
    private String userName;

    @TableField("charge_email_credit")
    private Integer chargeEmailCredit;


}
