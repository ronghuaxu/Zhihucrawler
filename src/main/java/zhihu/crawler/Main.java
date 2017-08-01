package zhihu.crawler;

import org.apache.http.client.methods.HttpPost;
import zhihu.crawler.db.DBinstore;
import zhihu.crawler.db.DataSourceUtil;
import zhihu.crawler.downloader.HttpDownload;
import zhihu.crawler.downloader.SimpleHttpExecutor;
import zhihu.crawler.model.SecondCategoryModel;
import zhihu.crawler.parser.ZhihuParser;
import zhihu.crawler.thread.StoreThread;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by huazi on 17/7/31.
 */
public class Main {

    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(20);

    private static final int[] FORSTCATEGORYS = {253,
            833,
            99,
            69,
            113,
            304,
            13908,
            570,
            1761,
            988,
            388,
            285,
            686,
            444,
            1537,
            3324,
            2955,
            4196,
            395,
            75,
            68,
            215,
            1027,
            445,
            112,
            237,
            1740,
            1538,
            2143,
            4217,
            2253,
            8437,
            19800
    };


    public static void main(String[] args) throws InterruptedException {
        DBinstore dBinstore = new DBinstore();
        try {
            dBinstore.setDataSource(DataSourceUtil.getDataSource(0));
        } catch (Exception e) {
            e.printStackTrace();
        }

        SecondCategoryModel secondCategory = new SecondCategoryModel();
        for (int t = 0; t < FORSTCATEGORYS.length; t++) {
            secondCategory.setFirstCa(FORSTCATEGORYS[t]);
            SecondCategoryModel.flag = -1;
            List<String> lists = null;
            do {

                //Post请求的参数
                Map<String, String> formparams = new HashMap<>();
                formparams.put("method", "next");
                formparams.put("params", secondCategory.getPostParam());
                HttpPost httpPost = null;
                //设置 request  post的请求头
                try {
                    httpPost = new HttpPost(new URI(SecondCategoryModel.FIXEDPOSTURL));
                    //                request = Request.Post(SecondCategoryModel.FIXEDPOSTURL)
                    httpPost.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
                    SimpleHttpExecutor.setParams(httpPost, formparams);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                lists = ZhihuParser.listParse(HttpDownload.getSimpleHttpExecutor().requestAsSting(httpPost));

                for (int i = 0; i < lists.size(); i++) {
                    fixedThreadPool.submit(new StoreThread(ZhihuParser.parse(lists.get(i)).setTopcategory(FORSTCATEGORYS[t]), dBinstore));
                }
                Thread.sleep(500 + (int) Math.random() * 1000);

            } while (lists.size() != 0);
        }
        //这样就可以做到终止线程池
        fixedThreadPool.shutdown();

    }


}
