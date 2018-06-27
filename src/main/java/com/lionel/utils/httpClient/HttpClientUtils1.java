package com.lionel.utils.httpClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class HttpClientUtils1 {
	
	
	/**
	 * post请求连接
	 * @param url 请求的地址
	 * @param params 参数map
	 * @param codeType 编码格式，utf-8
	 * @return
	 */
	public static String postData(String url,Map<String, String> params,String codeType) { 
		//创建httpclient连接
		HttpClient client=new HttpClient();
		//设置超时时间
		client.getHttpConnectionManager().getParams().setConnectionTimeout(10 * 1000);
		client.getHttpConnectionManager().getParams().setSoTimeout(10 * 1000);
		//创建postMethod对象
		PostMethod postMethod=new PostMethod(url);
		//将参数设置到post方法中
		if(params !=null) {
			//设置参数编码
			postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, codeType);
			//设置参数
			postMethod.setRequestBody(assembleRequestParams(params));
		}
		
		String result = "";
        try {
        	//访问url
        	client.executeMethod(postMethod);
        	//获取响应结果
            result = new String(postMethod.getResponseBody(), codeType);
        } catch (final Exception e) {
            
        } finally {
        	//关闭连接
        	postMethod.releaseConnection();
        }
		
		return result;
	}
	
	/**
     * 组装http请求参数
     *
     * @param
     * @param
     * @return
     */
    private synchronized static NameValuePair[] assembleRequestParams(Map<String, String> data) {
        final List<NameValuePair> nameValueList = new ArrayList<NameValuePair>();

        Iterator<Map.Entry<String, String>> it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
            nameValueList.add(new NameValuePair((String) entry.getKey(), (String) entry.getValue()));
        }

        return nameValueList.toArray(new NameValuePair[nameValueList.size()]);
    }

}
