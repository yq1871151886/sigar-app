package com.tiamaes.cloud.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author yangqigong
 * @version 1.0
 * @date 2020/4/22 18:47
 */
@TableName("XYT_APPLY_CARD")
@Data
public class CardModel {
    /**
     * 主键
     */
    @TableId(value = "ID",type = IdType.AUTO)
    private Integer id;

    /**
     * 主卡类型
     */
    @TableField(value = "CARD_TYPE")
    private String cardType;

    /**
     * 申请人姓名
     */
    @TableField(value = "APPLY_NAME")
    private String sname;

    /**
     * 申请人电话
     */
    @TableField(value = "MOBILE")
    private String sphone;

    @TableField(value = "USER_ID")
    private String userId;

    /**
     * app注册手机号
     */
    @TableField(value = "APP_PHONE")
    private String appPhone;

    /**
     *
     */
    @TableField(value = "REGISTER_DATE")
    @DateTimeFormat(pattern = "yyyy-HH-dd")
    private Date registerDate;
    /**
     * 身份证号
     */
    @TableField(value = "ID_CARD_NO")
    private String sidNo;

    /**
     * 持卡人证件类型
     * 00：身份证 01：军官证 02：护照 03：入境证 04：临时身份证 05：其他
     */
    @TableField(value = "SID_TYPE")
    private String sidType;

    /**
     * 地址
     */
    @TableField(value = "SAD_DRESS")
    private String saddRess;

    /**
     * 身份证正面
     */
    @TableField(value = "ID_CARD_IMAGE_FRONT")
    private String idCardImageFront;

    /**
     * 身份证反面
     */
    @TableField(value = "ID_CARD_IMAGE_BACK")
    private String idCardImageFrontBack;

    /**
     * 头像信息
     */
    @TableField(value = "HEAD_PIC")
    private String headPic;

    /**
     * 城市号
     */
    @TableField(value = "CITY_NO")
    private String cityNo;

    /**
     * 订单号
     */
    @TableField(value = "ORDER_NO")
    private String orderNo;

    /**
     * 一卡通流水号
     *
     */
    @TableField(value = "YKT_ORDER_NO")
    private String yktOrderNo;

    /**
     * 0没有审核通过，1审核通过已缴费，2审核通过未缴费 3审核失败
     */
    @TableField(value = "PAY_STATE")
    private Integer payState;

}
