package com.yupi.yumail.client.utils;

import com.yupi.yumail.client.constant.ExceptionEnum;
import com.yupi.yumail.client.model.base.BusinessException;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * HttpClient工具类（连接池）
 * @author Yupi Li
 * @date 19/04/04
 */
@Service
public class HttpClientUtils {


    private static HttpClientBuilder clientBuilder;

    private final static String APPLICATION_JSON = "application/json";

    // 初始化HttpClient建造者（连接池方式）
    static {
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
        manager.setMaxTotal(2000);
        manager.setDefaultMaxPerRoute(1000);
        clientBuilder = HttpClients.custom().setConnectionManager(manager).setDefaultHeaders(defaultHeader());
    }

    /**
     * get
     * @param url
     * @return
     * @throws IOException
     */
    public static String get(String url) throws IOException {
        CloseableHttpClient httpClient = clientBuilder.build();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = httpClient.execute(httpGet);
        return getResponseStr(response);
    }

    /**
     * post
     * @param url
     * @param data
     * @throws IOException
     */
    public static String post(String url, String data) throws IOException {
        CloseableHttpClient httpClient = clientBuilder.build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(data, ContentType.APPLICATION_JSON));
        HttpResponse response = httpClient.execute(httpPost);
        return getResponseStr(response);
    }

    /**
     * delete
     * @param url
     * @throws IOException
     */
    public static String delete(String url) throws IOException {
        CloseableHttpClient httpClient = clientBuilder.build();
        HttpDelete httpDelete = new HttpDelete(url);
        HttpResponse response = httpClient.execute(httpDelete);
        // 删除失败抛出异常，否则正常返回
        return getResponseStr(response);
    }

    /**
     * put
     * @param url
     * @param data
     * @throws IOException
     */
    public static String put(String url, String data) throws IOException {
        CloseableHttpClient httpClient = clientBuilder.build();
        HttpPut httpPut = new HttpPut(url);
        httpPut.setEntity(new StringEntity(data, ContentType.APPLICATION_JSON));
        HttpResponse response = httpClient.execute(httpPut);
        return getResponseStr(response);
    }

    /**
     * 获取响应字符串
     * @param res
     * @return
     * @throws IOException
     */
    private static String getResponseStr(HttpResponse res) throws IOException {
        int code = res.getStatusLine().getStatusCode();
        if (code != HTTP_OK) {
            throw new BusinessException(ExceptionEnum.HTTP_REQUEST_ERROR_CODE);
        }
        return EntityUtils.toString(res.getEntity(), StandardCharsets.UTF_8);
    }

    /**
     * 默认返回JSON
     * @return
     */
    private static List<Header> defaultHeader() {
        List<Header> headers = new ArrayList<>();
        Header header = new BasicHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON);
        headers.add(header);
        return headers;
    }
}