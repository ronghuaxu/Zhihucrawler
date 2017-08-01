package zhihu.crawler;

import org.apache.http.client.methods.HttpGet;
import zhihu.crawler.db.DBinstore;
import zhihu.crawler.db.DataSourceUtil;
import zhihu.crawler.downloader.HttpDownload;
import zhihu.crawler.model.TopicModel;
import zhihu.crawler.parser.ZhihuParser;
import zhihu.crawler.thread.GoodAnswerThread;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by huazi on 17/8/1.
 */
public class AcquireGoodAnswerMain {

    private final static String TEMPLATEURL = "https://www.zhihu.com%s/top-answers?page=%d";


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
        for (int t = 0; t < FORSTCATEGORYS.length; t++) {

            List<TopicModel> topicModelList = dBinstore.getAll(FORSTCATEGORYS[t]);
            //拼接出目标的精华帖的url
            for (TopicModel topicModel : topicModelList) {
                String targeturl = String.format(TEMPLATEURL, topicModel.getTopicurl(), 1);
                ZhihuParser.dBinstore = dBinstore;
                HttpGet httpGet = null;
                try {
                    httpGet = new HttpGet(new URI(targeturl));
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                //ture 代表需要获取页数 第一次请求
                int pages = ZhihuParser.answerParse2Db(HttpDownload.getSimpleHttpExecutor().requestAsSting(httpGet), topicModel.getSubcategory(), true);
                for (int i = 2; i <= pages; i++) {
                    String targeturl2 = String.format(TEMPLATEURL, topicModel.getTopicurl(), i);
                    HttpGet httpGet2 = null;
                    try {
                        httpGet2 = new HttpGet(new URI(targeturl2));
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    if (ZhihuParser.answerParse2Db(HttpDownload.getSimpleHttpExecutor().requestAsSting(httpGet2), topicModel.getSubcategory(), true) == 0) {
                        break;
                    }

                    Thread.sleep(1000 + (int) Math.random() * 1000);

                }
            }
        }
    }
}
