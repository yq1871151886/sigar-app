package com.tiamaes.cloud.beans;

public class CommReq {
	public String appid;
	public String mch_id;
	public String encrypted_data;
	public String iv;
	public String nonce_str;
	public String sign;
	public String GetSignData()
	{
		String sOriginalData = String.format("appid=%s&encrypted_data=%s&iv=%s&mch_id=%s&nonce_str=%s", appid, encrypted_data, iv, mch_id, nonce_str); 
		return sOriginalData;
	}
	
}
