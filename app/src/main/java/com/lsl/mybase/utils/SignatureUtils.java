package com.lsl.mybase.utils;

import android.text.TextUtils;
import android.util.Log;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * 公共签名计算 工具
 * 
 * @author Jack.Tan
 *
 */
public class SignatureUtils {


	private static final String TAG = "SignatureUtils";

	/**
	 * MD5 计算方法
	 * 
	 * @param parame  参数列表
	 * @param signType  签名类型 md5 or sha1
	 * @param key  为空 不参与 签名
	 * @param isEncode  是否进行encode
	 * @return
	 */
	public static String md5Sign(Map<String, Object> parame,String key) {
		return sign(parame,"MD5",key,false);
	}
	
	/**
	 * 公用签名计算方法
	 * 
	 * @param parame  参数列表
	 * @param signType  签名类型 md5 or sha1
	 * @param key  为空 不参与 签名
	 * @param isEncode  是否进行encode
	 * @return
	 */
	public static String sign(Map<String, Object> parame,String signType,String key,boolean
			isEncode) {

		String signStr = buildParams(parame, isEncode);
		String sign = null;

		if(!TextUtils.isEmpty(key)){
			signStr = signStr + "&key=" + key;
		}
		Log.d(TAG, "加密前: "+signStr);
		if(signType.equals("MD5")){
			sign =  MD5Utils.getMD5Str(signStr);
		}else if(signType.equals("SHA1")){
			sign =  Sha1Utils.getSha1Str(signStr);
		}
		return sign;
	}

	

	public static String buildParams(Map<String, Object> parame, boolean isEncode) {
		StringBuffer stringBuffer = new StringBuffer();
		List<String> keys = new ArrayList<String>(parame.keySet());
		Collections.sort(keys);
		for (String key : keys) {
			if(key.equals("sign")){
				continue;
			}
			if(parame.get(key) == null || "null".equals(parame.get(key).toString())){
				continue;
			}
			stringBuffer.append(key).append("=");
			if (isEncode) {
				stringBuffer.append(urlEncode(String.valueOf(parame.get(key))));
			} else {
				stringBuffer.append(String.valueOf(parame.get(key)));
			}
			stringBuffer.append("&");
		}
		stringBuffer.setLength(stringBuffer.length() - 1);
		return stringBuffer.toString();
	}

	/**
	 * 参数value url encode utf-8
	 * 
	 * @param str
	 * @return
	 */
	public static String urlEncode(String value) {
		try {
			return URLEncoder.encode(value, "UTF-8");
		} catch (Throwable e) {
			return value;
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println(Sha1Utils.getSha1Str("jsapi_ticket=kgt8ON7yVITDhtdwci0qebdVe2wgMPP_9fQSGv3yESwkY3MI6Qw6Mzt99s1Zuh9jbEPRrnmLpJ6hVH0eYt9Hzw&noncestr=OKHpYcg1pjMyjUDF&timestamp=1483505194&url=http://www.youyinceshi.com/themes/default/wxscan.html"));
		System.out.println(Sha1Utils.getSha1Str("jsapi_ticket=kgt8ON7yVITDhtdwci0qebdVe2wgMPP_9fQSGv3yESwkY3MI6Qw6Mzt99s1Zuh9jbEPRrnmLpJ6hVH0eYt9Hzw&nonceStr=OKHpYcg1pjMyjUDF&timestamp=1483505194&url=http://www.youyinceshi.com/themes/default/wxscan.html"));
	}
}
