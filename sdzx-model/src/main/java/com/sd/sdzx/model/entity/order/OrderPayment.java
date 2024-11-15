package com.sd.sdzx.model.entity.order;

import com.sd.sdzx.model.entity.pay.PaymentInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Schema(description = "订单支付实体类")
public class OrderPayment {
    private Long orderNo;
    private String nickName;
    private Long couponId;
    private BigDecimal totalAmount;
    private BigDecimal couponAmount;
    private BigDecimal originalTotalAmount;
    private BigDecimal feightFee;
    private Integer orderStatus;
    private String receiverName;
    private String receiverPhone;
    private String receiverProvince;
    private String receiverCity;
    private String receiverDistrict;
    private String receiverAddress;
    private Date paymentTime;
    private String remark;
    private Date cancelTime;
    private String cancelReason;
    private List<PaymentInfo> paymentInfo;
}
