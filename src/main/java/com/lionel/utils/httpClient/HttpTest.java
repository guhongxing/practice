package com.lionel.utils.httpClient;

import java.util.HashMap;
import java.util.Map;

public class HttpTest {

	public static void main(String[] args) {
		//httpUrlCoonectionTest();
		httpClientTest();
	}

	public static void httpClientTest() {
		/*
		 * String url="http://localhost:8080/mXuan/order/refund/logisticsCompany";
		 * Map<String, String> params=new HashMap<String, String>();
		 * params.put("gTicket", "10feb5f1d5ad4c5a8b4d8d3a77787dd4"); String
		 * codeType="utf-8"; String
		 * result=HttpClientUtils.postData(url,params,codeType);
		 * //System.out.println(result); HttpClientUtils.DataToMap(result);
		 */

		// 含有file参数的post请求
		String uploadUrl = "http://182.140.195.22:82/upload";
		Map<String, String> textParam = new HashMap<String, String>();
		textParam.put("fileName", "wa1213.jpg");
		textParam.put("udfd", "/refund/image/");
		textParam.put("verificationCode", "213212411121");
		textParam.put("newVerificationCode", "213212411121");
		String serverFieldName="name";
		String localFilePath="C:\\Users\\XBXM\\Desktop\\工作\\20180519002.jpg";
		try {
			String result=HttpClientUtils.uploadFile(uploadUrl, localFilePath, serverFieldName, textParam);
			String realPath=HttpClientUtils.DataToMap(result).get("path");
			System.out.println("图片在服务器的地址为："+ "http://182.140.195.22"+realPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void httpClient1Test() {
		String url = "http://localhost:8080/mXuan/order/refund/logisticsCompany";
		Map<String, String> params = new HashMap<String, String>();
		params.put("gTicket", "10feb5f1d5ad4c5a8b4d8d3a77787dd4");
		String codeType = "utf-8";
		String result = HttpClientUtils1.postData(url, params, codeType);
		System.out.println(result);
	}

	public static void httpUrlCoonectionTest() {
		// post方法测试
		// String url="http://localhost:8080/mXuan/order/refund/logisticsCompany";
		// Map<String, String> params=new HashMap<String, String>();
		// params.put("gTicket", "10feb5f1d5ad4c5a8b4d8d3a77787dd4");
		// String result=HttpURLConnectionUtils.postData(url,params);
		// System.out.println(result);

		// 含有file参数的post请求
		String uploadUrl = "http://182.140.195.22:82/upload";
		Map<String, String> textParam = new HashMap<String, String>();
		textParam.put("fileName", "wa1234.jpg");
		textParam.put("udfd", "/refund/image/");
		textParam.put("verificationCode", "213212411121");
		textParam.put("newVerificationCode", "213212411121");
		Map<String, String> pictureParam = new HashMap<String, String>();
		pictureParam.put("name", "C:\\Users\\XBXM\\Desktop\\工作\\20180519002.jpg");
		String result = HttpURLConnectionUtils.uploadPicture(uploadUrl, textParam, pictureParam);
		System.out.println("图片地址：182.140.195.22:82" + HttpURLConnectionUtils.DataToMap(result).get("path"));
	}

}
