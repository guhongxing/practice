package com.lionel.utils.image;

import org.apache.commons.lang.Validate;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;


/**
 * 
 * @author drywltman
 *
 */
public class HttpUtils {

	public static final int DEFAULT_TIMEOUT = 5000; // 5 seconds

	public static final String GBK = "GBK";

	public static final String UTF8 = "UTF-8";


	public static String postFile(String url, byte[] content, Map<String, String> paramsMap, String ext) throws Exception {
		Validate.notNull(url);
		try {
			HttpPost httpPost = new HttpPost(url);
			MultipartEntityBuilder mutiEntity = MultipartEntityBuilder.create();
			addMutiEntityPart(mutiEntity, paramsMap);
			ByteArrayBody image = new ByteArrayBody(content, ContentType.APPLICATION_JSON, "image." + ext);
			mutiEntity.addPart("name", image);
			DefaultHttpClient client = new DefaultHttpClient();
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 3000);
			HttpEntity reqEntity = mutiEntity.build();
			httpPost.setEntity(reqEntity);
			HttpResponse responseRes = null;
			try {
				responseRes = client.execute(httpPost);
			} catch (ClientProtocolException e) {
			} catch (IOException e) {
			} finally {
				client.close();
			}
			int status = responseRes.getStatusLine().getStatusCode();
			String resultStr = null;
			if (status == 200) {
				byte[] contents;
				try {
					contents = getContent(responseRes);
					resultStr = new String(contents, "utf-8");
				} catch (IOException e) {

				}
			}

			if (resultStr != null) {
				return resultStr;
			} else {
				return "";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	private static void addMutiEntityPart(MultipartEntityBuilder mutiEntity, Map<String, String> paramsMap)
			throws Exception {
		Iterator it = paramsMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			mutiEntity.addPart(entry.getKey().toString(),
					new StringBody(entry.getValue().toString(), ContentType.APPLICATION_JSON));
		}
	}
	private static byte[] getContent(HttpResponse response) throws IOException {
		InputStream result = null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				result = resEntity.getContent();
				int len = 0;
				while ((len = result.read()) != -1) {
					out.write(len);
				}
				return out.toByteArray();
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException("getContent异常", e);
		} finally {
			out.close();
			if (result != null) {
				result.close();
			}
		}
		return null;
	}

}
