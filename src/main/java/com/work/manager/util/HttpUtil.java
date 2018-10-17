package com.work.manager.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	
	
	/**
	 * GET
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String httpGet(String url) throws Exception {
			//创建HttpClient对象。 
	        CloseableHttpClient httpclients = HttpClients.createDefault();
	        //创建请求方法的实例，并指定请求URL。
	        HttpGet httpGet = new HttpGet(url);
	        // 执行请求
	        CloseableHttpResponse response = httpclients.execute(httpGet);
	        //获得相应
	        HttpEntity httpEntity = response.getEntity();
	        //读取响应
	        try{
	            HttpEntity entity = response.getEntity();
	            if(entity != null) {
	                InputStream is = entity.getContent();
	                BufferedReader reader = new BufferedReader(new InputStreamReader(is));   
	                StringBuilder sb = new StringBuilder();   
	                String line = null;   
	                while ((line = reader.readLine()) != null) {   
	                	sb.append(line);   
	                }   
	                return sb.toString();   
	            }
	        }finally{
	            response.close();
	        }
	        return EntityUtils.toString(httpEntity,"UTF-8");
	}	

	/**
	 * post请求 
	 * 
	 * @param url
	 * @param formparams
	 * @return
	 * @throws Exception
	 */
	public static String httpPost(String url,List<NameValuePair> formparams) throws Exception {
		//创建HttpClient访问对象
	    CloseableHttpClient httpclients = HttpClients.createDefault();
	    //创建请求方法的实例，并指定请求URL。
	    HttpPost httpPost = new HttpPost(url);
	    //处理参数
	    UrlEncodedFormEntity formentity = new UrlEncodedFormEntity(formparams,Consts.UTF_8);
	    //请求设置参数
	    httpPost.setEntity(formentity);
	    //执行请求
	    CloseableHttpResponse response = httpclients.execute(httpPost);
	    //获取响应
	    HttpEntity httpEntity = response.getEntity();
	    //处理响应
	    try{
            HttpEntity entity = response.getEntity();
            if(entity != null) {
                InputStream is = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));   
                StringBuilder sb = new StringBuilder();   
                String line = null;   
                while ((line = reader.readLine()) != null) {   
                	sb.append(line);   
                }   
                return sb.toString();   
            }
        }finally{
            response.close();
        }
	    return EntityUtils.toString(httpEntity);
	}

}
