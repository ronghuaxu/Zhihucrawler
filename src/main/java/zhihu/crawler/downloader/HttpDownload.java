package zhihu.crawler.downloader;


import org.apache.http.client.methods.HttpRequestBase;

/**
 * Created by huazi on 17/7/31.
 */
public class HttpDownload {
    private static SimpleHttpExecutor simpleHttpExecutor = new SimpleHttpExecutor();


    public static String download(HttpRequestBase httpRequestBase) {
        return simpleHttpExecutor.requestAsSting(httpRequestBase);
    }


    public static SimpleHttpExecutor getSimpleHttpExecutor() {
        return simpleHttpExecutor;
    }

    public void setSimpleHttpExecutor(SimpleHttpExecutor simpleHttpExecutor) {
        this.simpleHttpExecutor = simpleHttpExecutor;
    }
}
