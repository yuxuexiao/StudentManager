package com.tools;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

/*
 * 
 *  参数变量 	说明
	Gbk编码Url 	http://gbk.sms.webchinese.cn/
	Utf-8编码Url 	http://utf8.sms.webchinese.cn/
	Uid 	本站用户名（如您无本站用户名请先注册）[免费注册]
	Key 	注册时填写的接口秘钥（可到用户平台修改接口秘钥）
	如需要加密参数，请把Key变量名改成KeyMD5，
	KeyMD5=接口秘钥32位MD5加密，大写。
	smsMob 	目的手机号码（多个手机号请用半角逗号隔开）
	smsText 短信内容，最多支持400个字，普通短信70个字/条，长短信64个字/条计费
 * 
 */

public class SendMsg_webchinese {
	
//	public static void main(String[] args) {
//		System.out.println(YZM().toString());
//		try {
//			//使用时有一个条件就是必须联网
//			//需要引入三个jar包commons-codec-1.4.jar、commons-httpclient-3.1.jar、commons-logging-1.1.1.jar
//			//使用SendMsg_webchinese.SendMessage("手机号");的方式即可发送短信
//			String smsText = "验证码【"+YZM()+"】";
//			SendMsg_webchinese.SendMessage("18814663440",smsText);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	/**
	 * 发送短信功能
	 * @param phonenumber 电话号码
	 * @param smsText 短信内容
	 * @throws Exception 
	 */
	public static void SendMessage(String phonenumber,String smsText) throws Exception {
		
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://utf8.sms.webchinese.cn/");//这里我使用的是utf编码的url
		post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf8");// 在头文件中设置转码
		//Uid注册账号zhuoshi   Key秘钥12e8bae0bd4a9dd269b0  
		NameValuePair[] data = { new NameValuePair("Uid", "zhuoshi"), new NameValuePair("Key", "12e8bae0bd4a9dd269b0 "),
				new NameValuePair("smsMob", phonenumber), new NameValuePair("smsText", smsText) };
		post.setRequestBody(data);

		 
		client.executeMethod(post);//发送短信
		Header[] headers = post.getResponseHeaders();//拿到了响应的结果
		int statusCode = post.getStatusCode();
		System.out.println("statusCode:" + statusCode);//当前短信的id
		for (Header h : headers) {
			System.out.println(h.toString());//响应的结果
		}
		String result = new String(post.getResponseBodyAsString().getBytes("utf8"));
		System.out.println(result);//发送短信的结果
		
		/*
		 * result返还结果String格式
		 * 短信发送后返回值 	说　明
			-1 				没有该用户账户
			-2 				接口密钥不正确 [查看密钥]不是账户登陆密码
			-21 			MD5接口密钥加密不正确
			-3 				短信数量不足
			-11 			该用户被禁用
			-14 			短信内容出现非法字符
			-4 				手机号格式不正确
			-41 			手机号码为空
			-42 			短信内容为空
			-51 			短信签名格式不正确 接口签名格式为：【签名内容】
			-6 				IP限制
			大于0 			短信发送数量
		 * 
		 */

		post.releaseConnection();

	}

	public static String YZM() {

		int a = (int) (Math.random() * 10);
		int b = (int) (Math.random() * 10);
		int c = (int) (Math.random() * 10);
		int d = (int) (Math.random() * 10);
		int e = (int) (Math.random() * 10);
		String yzm = a + "" + b + "" + c + "" + d + "" + e + "";
		return yzm;
	}

}