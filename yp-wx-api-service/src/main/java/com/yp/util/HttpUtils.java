package com.yp.util;


import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * HTTP请求工具类：发送阿里请求推荐使用的工具类
 *
 * @author zhongli
 */
public class HttpUtils {

    public HttpUtils() {
    }

    public static HttpResponse doGet(String host, String path, String method, Map<String, String> headers, Map<String, String> querys) throws Exception {
        HttpClient httpClient = wrapClient(host);
        HttpGet request = new HttpGet(buildUrl(host, path, querys));
        Iterator var7 = headers.entrySet().iterator();

        while(var7.hasNext()) {
            Map.Entry<String, String> e = (Map.Entry)var7.next();
            request.addHeader((String)e.getKey(), (String)e.getValue());
        }

        return httpClient.execute(request);
    }

    public static HttpResponse doPost(String host, String path, String method, Map<String, String> headers, Map<String, String> querys, Map<String, String> bodys) throws Exception {
        HttpClient httpClient = wrapClient(host);
        HttpPost request = new HttpPost(buildUrl(host, path, querys));
        Iterator var8 = headers.entrySet().iterator();

        while(var8.hasNext()) {
            Map.Entry<String, String> e = (Map.Entry)var8.next();
            request.addHeader((String)e.getKey(), (String)e.getValue());
        }

        if (bodys != null) {
            List<NameValuePair> nameValuePairList = new ArrayList();
            Iterator var12 = bodys.keySet().iterator();

            while(var12.hasNext()) {
                String key = (String)var12.next();
                nameValuePairList.add(new BasicNameValuePair(key, (String)bodys.get(key)));
            }

            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nameValuePairList, "utf-8");
            formEntity.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
            request.setEntity(formEntity);
        }

        return httpClient.execute(request);
    }

    public static HttpResponse doPost(String host, String path, String method, Map<String, String> headers, Map<String, String> querys, String body) throws Exception {
        HttpClient httpClient = wrapClient(host);
        HttpPost request = new HttpPost(buildUrl(host, path, querys));
        Iterator var8 = headers.entrySet().iterator();

        while(var8.hasNext()) {
            Map.Entry<String, String> e = (Map.Entry)var8.next();
            request.addHeader((String)e.getKey(), (String)e.getValue());
        }

        if (StringUtils.isNotEmpty(body)) {
            request.setEntity(new StringEntity(body, "utf-8"));
        }

        return httpClient.execute(request);
    }

    public static HttpResponse doPost(String host, String path, String method, Map<String, String> headers, Map<String, String> querys, byte[] body) throws Exception {
        HttpClient httpClient = wrapClient(host);
        HttpPost request = new HttpPost(buildUrl(host, path, querys));
        Iterator var8 = headers.entrySet().iterator();

        while(var8.hasNext()) {
            Map.Entry<String, String> e = (Map.Entry)var8.next();
            request.addHeader((String)e.getKey(), (String)e.getValue());
        }

        if (body != null) {
            request.setEntity(new ByteArrayEntity(body));
        }

        return httpClient.execute(request);
    }

    public static HttpResponse doPut(String host, String path, String method, Map<String, String> headers, Map<String, String> querys, String body) throws Exception {
        HttpClient httpClient = wrapClient(host);
        HttpPut request = new HttpPut(buildUrl(host, path, querys));
        Iterator var8 = headers.entrySet().iterator();

        while(var8.hasNext()) {
            Map.Entry<String, String> e = (Map.Entry)var8.next();
            request.addHeader((String)e.getKey(), (String)e.getValue());
        }

        if (StringUtils.isNotEmpty(body)) {
            request.setEntity(new StringEntity(body, "utf-8"));
        }

        return httpClient.execute(request);
    }

    public static HttpResponse doPut(String host, String path, String method, Map<String, String> headers, Map<String, String> querys, byte[] body) throws Exception {
        HttpClient httpClient = wrapClient(host);
        HttpPut request = new HttpPut(buildUrl(host, path, querys));
        Iterator var8 = headers.entrySet().iterator();

        while(var8.hasNext()) {
            Map.Entry<String, String> e = (Map.Entry)var8.next();
            request.addHeader((String)e.getKey(), (String)e.getValue());
        }

        if (body != null) {
            request.setEntity(new ByteArrayEntity(body));
        }

        return httpClient.execute(request);
    }

    public static HttpResponse doDelete(String host, String path, String method, Map<String, String> headers, Map<String, String> querys) throws Exception {
        HttpClient httpClient = wrapClient(host);
        HttpDelete request = new HttpDelete(buildUrl(host, path, querys));
        Iterator var7 = headers.entrySet().iterator();

        while(var7.hasNext()) {
            Map.Entry<String, String> e = (Map.Entry)var7.next();
            request.addHeader((String)e.getKey(), (String)e.getValue());
        }

        return httpClient.execute(request);
    }

    private static String buildUrl(String host, String path, Map<String, String> querys) throws UnsupportedEncodingException {
        StringBuilder sbUrl = new StringBuilder();
        sbUrl.append(host);
        if (!StringUtils.isNotEmpty(path)) {
            sbUrl.append(path);
        }

        if (null != querys) {
            StringBuilder sbQuery = new StringBuilder();
            Iterator var5 = querys.entrySet().iterator();

            while(var5.hasNext()) {
                Map.Entry<String, String> query = (Map.Entry)var5.next();
                if (0 < sbQuery.length()) {
                    sbQuery.append("&");
                }

                if (StringUtils.isNotEmpty((CharSequence)query.getKey()) && !StringUtils.isNotEmpty((CharSequence)query.getValue())) {
                    sbQuery.append((String)query.getValue());
                }

                if (!StringUtils.isNotEmpty((CharSequence)query.getKey())) {
                    sbQuery.append((String)query.getKey());
                    if (!StringUtils.isNotEmpty((CharSequence)query.getValue())) {
                        sbQuery.append("=");
                        sbQuery.append(URLEncoder.encode((String)query.getValue(), "utf-8"));
                    }
                }
            }

            if (0 < sbQuery.length()) {
                sbUrl.append("?").append(sbQuery);
            }
        }

        return sbUrl.toString();
    }

    private static HttpClient wrapClient(String host) {
        HttpClient httpClient = new DefaultHttpClient();
        if (host.startsWith("https://")) {
            sslClient(httpClient);
        }

        return httpClient;
    }

    private static void sslClient(HttpClient httpClient) {
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] xcs, String str) {
                }

                public void checkServerTrusted(X509Certificate[] xcs, String str) {
                }
            };
            ctx.init((KeyManager[])null, new TrustManager[]{tm}, (SecureRandom)null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx);
            ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm = httpClient.getConnectionManager();
            SchemeRegistry registry = ccm.getSchemeRegistry();
            registry.register(new Scheme("https", 443, ssf));
        } catch (KeyManagementException var6) {
            throw new RuntimeException(var6);
        } catch (NoSuchAlgorithmException var7) {
            throw new RuntimeException(var7);
        }
    }

    public static String sendPost(String url, Map<String, ?> paramMap) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        String param = "";

        String key;
        for(Iterator it = paramMap.keySet().iterator(); it.hasNext(); param = param + key + "=" + paramMap.get(key) + "&") {
            key = (String)it.next();
        }

        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();

            String line;
            for(in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")); (line = in.readLine()) != null; result = result + line) {
            }
        } catch (Exception var18) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }

                if (in != null) {
                    in.close();
                }
            } catch (IOException var17) {
                var17.printStackTrace();
            }

        }
        return result;
    }
}
