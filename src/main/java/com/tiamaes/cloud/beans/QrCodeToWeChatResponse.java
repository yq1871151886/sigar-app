package com.tiamaes.cloud.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 微信小程序获取乘车码返回实体
 * @author yangqigong
 * @date 2020年5月21日
 */
@Data
@ToString
public class QrCodeToWeChatResponse {

	/**
	 * 错误码
	 */
	private Integer errcode;

	/**
	 * 错误信息
	 */
	private String errmsg;

	/**
	 * 随机串
	 */
	@JsonProperty("nonce_str")
	private String nonceStr;

	/**
	 * 数据体
	 */
	@JsonProperty("encrypted_data")
	private String encryptedData;

	/**
	 * 向量
	 */
	private String iv;
	
	
	
	
}
