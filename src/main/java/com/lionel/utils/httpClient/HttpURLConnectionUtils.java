package com.lionel.utils.httpClient;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.activation.MimetypesFileTypeMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class HttpURLConnectionUtils {

	/**
	 * get和post方式的请求
	 * @param urlPath
	 * @param params
	 * @param method
	 * @return
	 */
	public static String requestData(String urlPath, Map<String, String> params, String method) {
		String msg = "";
		try {
			// 获取访问地址
			URL url;
			// 创建HttpURLConnection对象
			HttpURLConnection connection;

			// 设置请求方式
			if (method.equals("POST")) {
				url = new URL(urlPath);
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");// 大写
			} else {// get方式
				String path = urlPath + getParamsString(params);
				path.substring(0, path.length() - 1);
				url = new URL(path);
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");// 大写
			}
			// 设置超时时间
			connection.setConnectTimeout(3000);
			// 设置是否输入输出
			connection.setDoOutput(true);
			connection.setDoInput(true);
			// 设置此 HttpURLConnection 实例是否应该自动执行 HTTP 重定向
			connection.setInstanceFollowRedirects(true);
			// 设置使用标准编码格式编码参数的名-值对
			connection.setRequestProperty("Content-Type", "application/Json; charset=UTF-8");

			// 连接
			connection.connect();

			// 写入参数
			if (method.equals("POST")) {
				OutputStream out = connection.getOutputStream();
				out.write(mapToJsonString(params).getBytes());
				out.flush();
				out.close();
			}

			// 获取响应信息

			int code = connection.getResponseCode();
			if (code == 200) {// 响应成功
				// 创建输入流来获取响应结果
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				while ((line = reader.readLine()) != null) {
					msg += line + "\n";
				}
				reader.close();
			}

			// 断开连接
			connection.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return msg;

	}

	/**
	 * post请求方式访问地址
	 * 
	 * @param urlPath
	 *            请求地址
	 * @param params
	 *            参数map
	 * @return
	 */
	public static String postData(String urlPath, Map<String, String> params) {

		String msg = "";
		try {
			// 获取访问地址
			URL url = new URL(urlPath);
			// 创建HttpURLConnection对象
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// 设置请求方式
			connection.setRequestMethod("POST");// 大写
			// 设置超时时间
			connection.setConnectTimeout(3000);
			// 设置是否输入输出
			connection.setDoOutput(true);
			connection.setDoInput(true);
			// 设置此 HttpURLConnection 实例是否应该自动执行 HTTP 重定向
			connection.setInstanceFollowRedirects(true);
			// 设置使用标准编码格式编码参数的名-值对
			connection.setRequestProperty("Content-Type", "application/Json; charset=UTF-8");

			// 连接
			connection.connect();

			// 写入参数
			OutputStream out = connection.getOutputStream();
			out.write(mapToJsonString(params).getBytes());
			out.flush();
			out.close();

			// 获取响应信息

			int code = connection.getResponseCode();
			if (code == 200) {// 响应成功
				// 创建输入流来获取响应结果
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				while ((line = reader.readLine()) != null) {
					msg += line + "\n";
				}
				reader.close();
			}

			// 断开连接
			connection.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return msg;
	}

	public static String getParamsString(Map<String, String> params) {
		StringBuffer msg = new StringBuffer("?");
		Iterator iterator = params.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			String inputName = (String) entry.getKey();
			String inputValue = (String) entry.getValue();
			msg.append(inputName + "=" + inputValue + "&");
		}
		return msg.toString();
	}

	/**
	 * 上传图片
	 * 
	 * @param urlStr
	 * @param textMap
	 * @param fileMap
	 * @return
	 */
	public static String uploadPicture(String urlStr, Map<String, String> textMap, Map<String, String> fileMap) {
		String res = "";
		HttpURLConnection conn = null;
		String BOUNDARY = "---------------------------123821742118716"; // boundary就是request头和上传文件内容的分隔符
		try {
			// 创建连接，并设置参数
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

			OutputStream out = new DataOutputStream(conn.getOutputStream());
			// 文本参数的写入
			if (textMap != null) {
				StringBuffer strBuf = new StringBuffer();
				Iterator iter = textMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"\r\n\r\n");
					strBuf.append(inputValue);
				}
				out.write(strBuf.toString().getBytes());
			}

			// 文件参数的写入
			if (fileMap != null) {
				Iterator iter = fileMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					File file = new File(inputValue);
					String filename = file.getName();
					String contentType = new MimetypesFileTypeMap().getContentType(file);
					if (filename.endsWith(".png")) {
						contentType = "image/png";
					}
					if (contentType == null || contentType.equals("")) {
						contentType = "application/octet-stream";
					}

					StringBuffer strBuf = new StringBuffer();
					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"; filename=\"" + filename
							+ "\"\r\n");
					strBuf.append("Content-Type:" + contentType + "\r\n\r\n");

					out.write(strBuf.toString().getBytes());

					DataInputStream in = new DataInputStream(new FileInputStream(file));
					int bytes = 0;
					byte[] bufferOut = new byte[1024];
					while ((bytes = in.read(bufferOut)) != -1) {
						out.write(bufferOut, 0, bytes);
					}
					in.close();
				}
			}

			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
			out.write(endData);
			out.flush();
			out.close();

			// 读取返回数据
			int code = conn.getResponseCode();
			if (code == 200) {// 响应成功
				StringBuffer strBuf = new StringBuffer();
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line = null;
				while ((line = reader.readLine()) != null) {
					strBuf.append(line).append("\n");
				}
				res = strBuf.toString();
				reader.close();
				reader = null;
			} else {
				res = "访问错误";
			}
		} catch (Exception e) {
			System.out.println("发送POST请求出错。" + urlStr);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		return res;
	}

	public static String mapToJsonString(Map<String, String> map) {

		return JSON.toJSONString(map);
	}

	public static Map<String, String> DataToMap(String jsonData) {
		Map<String, String> resultMap = new HashMap<String, String>();
		JSONObject obj = JSON.parseObject(jsonData);
		Set<String> keys = obj.keySet();
		for (String key : keys) {
			resultMap.put(key, obj.getString(key));
		}

		return resultMap;
	}
}
