package zhihu.crawler.parser;

import com.alibaba.fastjson.JSON;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import zhihu.crawler.db.DBinstore;
import zhihu.crawler.model.GlobalModel;
import zhihu.crawler.model.GoodAnswerModle;
import zhihu.crawler.model.Response;
import zhihu.crawler.model.TopicModel;

import java.util.List;

/**
 * Created by huazi on 17/7/31.
 */
public class ZhihuParser {

    public static DBinstore dBinstore;


    public static TopicModel parse(String res) {
        Document dc = Jsoup.parse(res);
        return new TopicModel(dc.select("a").attr("href").replaceAll("\\\\\"", ""), dc.select("strong").text());
    }

    public static List<String> listParse(String res) {
        return JSON.parseObject(res, Response.class).getMsg();
    }

    public static int answerParse2Db(String res, String subcategory, boolean flag) {
        Document dc = Jsoup.parse(res);

        Elements elements = dc.select(".feed-main>.content");

        for (Element element : elements) {
            GoodAnswerModle goodAnswerModle = new GoodAnswerModle();
            goodAnswerModle.setSubcategory(subcategory);
            goodAnswerModle.setBesturl(element.select("div.expandable.entry-body > link").attr("href"));
            goodAnswerModle.setQuestiontitle(element.select("h2>a").text());
            String temp = element.select("div.zm-item-vote > a").text();
            int upagree;
            if (temp.contains("K")) {
                upagree = Integer.valueOf(temp.replace("K", "")) * 1000;

            } else {
                upagree = Integer.valueOf(temp);
            }
            if (upagree < GlobalModel.THRESHOLD) {
                return 0;
            }
            goodAnswerModle.setUpagree(upagree);

            //存储到数据库
            dBinstore.insertGoodAnswer(goodAnswerModle);
        }
        if (flag) {
            String page = dc.select(".zm-invite-pager>span:nth-last-child(2)").text();
            if (page.length() == 0) {
                return 0;
            }
            return Integer.valueOf(page);
        }
        return 0;
    }


}
