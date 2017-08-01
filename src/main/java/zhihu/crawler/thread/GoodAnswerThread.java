package zhihu.crawler.thread;

import org.apache.http.client.methods.HttpGet;
import zhihu.crawler.downloader.HttpDownload;
import zhihu.crawler.parser.ZhihuParser;

/**
 * Created by huazi on 17/8/1.
 */
public class GoodAnswerThread extends Thread {

    private HttpGet request;
    private String subcategory;

    public GoodAnswerThread(HttpGet request, String subcategory) {
        this.request = request;
        this.subcategory = subcategory;
    }

    @Override
    public void run() {
        for (int j = 0; j < 3; j++) {

            try {
                ZhihuParser.answerParse2Db(HttpDownload.getSimpleHttpExecutor().requestAsSting(request), subcategory, false);
                break;
            } catch (Exception e) {
                if (j == 2) {
                    System.out.println(request.toString() + "下载失败");
                }
            }
        }
    }
}
