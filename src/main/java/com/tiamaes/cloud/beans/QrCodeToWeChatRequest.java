package com.tiamaes.cloud.beans;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 微信小程序获取乘车码请求体
 * @author yangqigong
 * @date 2020年5月21日
 */
@Data
public class QrCodeToWeChatRequest{

	/**
	 * 商户号
	 */
	@JsonProperty("appid")
	private String appId;

	/**
	 * mch_id
	 */
	@JsonProperty("mch_id")
	private String mchId;

	/**
	 * 随机字符串
	 */
	@JsonProperty("nonce_str")
	private String nonceStr;

	/**
	 * encrypted_data
	 */
	@JsonProperty("encrypted_data")
	private String encryptedData;

	/**
	 * 向量
	 */
		private String iv;

	/**
	 * 签名
	 */
	private String sign;

}
