package zhihu.crawler.downloader;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by huazi on 17/8/1.
 */
public class SimpleHttpExecutor {


    private CloseableHttpClient httpClient = null;

    public SimpleHttpExecutor() {
        init();
    }

    private static void config(HttpRequestBase httpRequestBase) {
        // 配置请求的超时设置
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(10000)
                .setConnectTimeout(10000)
                .setSocketTimeout(10000)
                .build();
        httpRequestBase.setConfig(requestConfig);
    }

    public SimpleHttpExecutor init() {
        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
        LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", plainsf)
                .register("https", sslsf)
                .build();

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
        // 将最大连接数增加到200
        cm.setMaxTotal(200);
        // 将每个路由基础的连接增加到20
        cm.setDefaultMaxPerRoute(20);

        httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setRetryHandler(new DefaultHttpRequestRetryHandler())
                .build();

        return this;

    }

    public String requestAsSting(HttpRequestBase httpRequestBase) {
        config(httpRequestBase);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpRequestBase);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();
        try {
            return EntityUtils.toString(entity, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static HttpRequestBase setParams(HttpPost httpRequestBase, Map<String, String> map) throws UnsupportedEncodingException {
        //设置参数
        List<NameValuePair> list = new ArrayList<>();
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> elem = (Map.Entry<String, String>) iterator.next();
            list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
        }

        if (list.size() > 0) {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "utf-8");
            httpRequestBase.setEntity(entity);
        }
        return httpRequestBase;
    }

}
