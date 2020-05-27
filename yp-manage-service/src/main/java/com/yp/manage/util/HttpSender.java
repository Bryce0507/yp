package com.yp.manage.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * http工具
 */
public class HttpSender {

    private static Logger log = LoggerFactory.getLogger(HttpSender.class);

    // 连接超时时间
    private static final int CONNECTION_TIMEOUT = 3000;
    // 参数编码
    private static final String ENCODE_CHARSET = "utf-8";


    public static StringBuffer postRequest(String url,String postData) throws Exception {
        StringBuffer sb = new StringBuffer();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        log.info("post请求的参数为>>>" + postData);
        try {
            HttpPost httpPost = new HttpPost(url);
            StringEntity input = new StringEntity(postData,"UTF-8");
            input.setContentType("application/json");
            input.setContentEncoding("UTF-8");
            httpPost.setEntity(input);
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                System.out.println(response.getStatusLine());
                HttpEntity entity = response.getEntity();
                long len = entity.getContentLength();
                if(len > -1){
                    String content = EntityUtils.toString(entity,"utf-8");
                    sb.append(content);
                }
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
        log.info("post请求的结果为>>>" + sb.toString());
        return sb;
    }


    public static String getRequest(String httpUrl, String... params) {
        StringBuilder resultData = new StringBuilder();
        URL url = null;
        try {

            String paramurl = sendGetParams(httpUrl, params);
            url = new URL(paramurl);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection urlConn = null;
        InputStreamReader in = null;
        BufferedReader buffer = null;
        String inputLine = null;
        if (url != null) {
            try {
                urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setRequestMethod("GET");
                urlConn.setConnectTimeout(CONNECTION_TIMEOUT);
                in = new InputStreamReader(urlConn.getInputStream(),
                        HttpSender.ENCODE_CHARSET);
                buffer = new BufferedReader(in);
                if (urlConn.getResponseCode() == 200) {
                    while ((inputLine = buffer.readLine()) != null) {
                        resultData.append(inputLine);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (buffer != null) {
                        buffer.close();
                    }

                    if (in != null) {
                        in.close();
                    }

                    if (urlConn != null) {
                        urlConn.disconnect();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return resultData.toString();
    }


    /**
     * Get追加参数
     *
     * @see <code>params</code>
     * @param reqURL
     *            请求地址
     * @param params
     *            发送到远程主机的正文数据[a:1,b:2]
     * @return
     */
    private static String sendGetParams(String reqURL, String... params) {
        StringBuilder sbd = new StringBuilder(reqURL);
        if (params != null && params.length > 0) {
            if (isexist(reqURL, "?")) {// 存在?
                sbd.append("&");
            } else {
                sbd.append("?");
            }
            for (int i = 0; i < params.length; i++) {
                String[] temp = params[i].split(":");
                sbd.append(temp[0]);
                sbd.append("=");
                sbd.append(urlEncode(temp[1]));
                sbd.append("&");

            }
            sbd.setLength(sbd.length() - 1);// 删掉最后一个
        }
        return sbd.toString();
    }


    // 查找某个字符串是否存在
    private static boolean isexist(String str, String fstr) {
        return str.indexOf(fstr) == -1 ? false : true;
    }

    /**
     * 编码
     *
     * @param source
     * @return
     */
    private static String urlEncode(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder
                    .encode(source, HttpSender.ENCODE_CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

}
