package com.lionel.utils.httpClient;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * HttpClient连接工具
 * @author lionel
 * 
 *
 */
public class HttpClientUtils {

	
	/**
	 * post方式请求获取响应结果
	 * @param url 请求地址
	 * @param params 参数map
	 * @param codeType 编码格式 utf-8
	 * @return
	 */
	public static String postData(String url,Map<String, String> params,String codeType) {
		// 创建默认的httpClient实例.    
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //创建httpPost
        HttpPost httpPost=new HttpPost(url);
        
        //封装参数
        List<BasicNameValuePair> paramsList=assembleRequestParams(params);
        //创建entity
        UrlEncodedFormEntity uefEntity; 
        String result="";
        try {
			uefEntity=new UrlEncodedFormEntity(paramsList, codeType);
			//设置参数
			httpPost.setEntity(uefEntity); 
			//请求响应，获取响应结果
			CloseableHttpResponse response = httpclient.execute(httpPost); 
			
			 HttpEntity entity = response.getEntity();  
             if (entity != null) {  
                 //结果装换为String
            	 result=EntityUtils.toString(entity, "UTF-8");
             }
		} catch (Exception e) {
			e.printStackTrace();
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
    private synchronized static List<BasicNameValuePair> assembleRequestParams(Map<String, String> data) {
        final List<BasicNameValuePair> nameValueList = new ArrayList<BasicNameValuePair>();

        Iterator<Map.Entry<String, String>> it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
            nameValueList.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
        }

        return nameValueList;
    }
    
    public static Map<String,String> DataToMap(String jsonData){
    	Map<String, String> resultMap=new HashMap<String,String>();
    	JSONObject obj= JSON.parseObject(jsonData);
    	Set<String> keys=obj.keySet();
    	for (String key : keys) {
    		resultMap.put(key, obj.getString(key));
		}
    	
    	return resultMap;
    }
    
    /**
     * 上传文件
     *
     * @param serverUrl
     *            服务器地址
     * @param localFilePath
     *            本地文件路径
     * @param serverFieldName
     * @param params
     * @return
     * @throws Exception
     */
    public static String uploadFile(String serverUrl, String localFilePath,
                                 String serverFieldName, Map<String, String> params)
            throws Exception {
        String respStr = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httppost = new HttpPost(serverUrl);
            FileBody binFileBody = new FileBody(new File(localFilePath));

            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            // add the file params
            multipartEntityBuilder.addPart(serverFieldName, binFileBody);
            // 设置上传的其他参数
            setUploadParams(multipartEntityBuilder, params);

            HttpEntity reqEntity = multipartEntityBuilder.build();
            httppost.setEntity(reqEntity);

            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                System.out.println(response.getStatusLine());
                HttpEntity resEntity = response.getEntity();
                respStr = getRespString(resEntity);
                EntityUtils.consume(resEntity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
        System.out.println("resp=" + respStr);
        return respStr;
    }

    /**
     * 设置上传文件时所附带的其他参数
     *
     * @param multipartEntityBuilder
     * @param params
     */
    private static void setUploadParams(MultipartEntityBuilder multipartEntityBuilder,
                                 Map<String, String> params) {
        if (params != null && params.size() > 0) {
            Set<String> keys = params.keySet();
            for (String key : keys) {
                multipartEntityBuilder.addPart(key, new StringBody(params.get(key),
                                ContentType.TEXT_PLAIN));
            }
        }
    }
    
    /**
     * 将返回结果转化为String
     *
     * @param entity
     * @return
     * @throws Exception
     */
    private  static String getRespString(HttpEntity entity) throws Exception {
        if (entity == null) {
            return null;
        }
        InputStream is = entity.getContent();
        StringBuffer strBuf = new StringBuffer();
        byte[] buffer = new byte[4096];
        int r = 0;
        while ((r = is.read(buffer)) > 0) {
            strBuf.append(new String(buffer, 0, r, "UTF-8"));
        }
        return strBuf.toString();
    }

}
