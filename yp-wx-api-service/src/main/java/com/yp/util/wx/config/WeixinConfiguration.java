package com.yp.util.wx.config;

import java.util.ResourceBundle;

/**
 * 微信配置信息
 */

public class WeixinConfiguration {
	//加载属性文件
	private static ResourceBundle res = ResourceBundle.getBundle("weixin/app");
	//工具方法:取值
	public static String get(String key){
		return res.getString(key);
	}
	public static String appID=get("appID");
    
	public static String appSecret=get("appSecret");
}
