package zhihu.crawler.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Created by huazi on 17/7/31.
 */
public class FirstCategory {

    public static void main(String[] args) {

        String res = "<ul class=\"zm-topic-cat-main clearfix\">\n" +
                "\n" +
                "<li class=\"zm-topic-cat-item\" data-id=\"253\"><a href=\"#游戏\">游戏</a></li>\n" +
                "\n" +
                "<li class=\"zm-topic-cat-item\" data-id=\"833\"><a href=\"#运动\">运动</a></li>\n" +
                "\n" +
                "<li class=\"zm-topic-cat-item\" data-id=\"99\"><a href=\"#互联网\">互联网</a></li>\n" +
                "\n" +
                "<li class=\"zm-topic-cat-item\" data-id=\"69\"><a href=\"#艺术\">艺术</a></li>\n" +
                "\n" +
                "<li class=\"zm-topic-cat-item\" data-id=\"113\"><a href=\"#阅读\">阅读</a></li>\n" +
                "\n" +
                "<li class=\"zm-topic-cat-item\" data-id=\"304\"><a href=\"#美食\">美食</a></li>\n" +
                "\n" +
                "<li class=\"zm-topic-cat-item\" data-id=\"13908\"><a href=\"#动漫\">动漫</a></li>\n" +
                "\n" +
                "<li class=\"zm-topic-cat-item\" data-id=\"570\"><a href=\"#汽车\">汽车</a></li>\n" +
                "\n" +
                "<li class=\"zm-topic-cat-item\" data-id=\"1761\"><a href=\"#生活方式\">生活方式</a></li>\n" +
                "\n" +
                "<li class=\"zm-topic-cat-item\" data-id=\"988\"><a href=\"#教育\">教育</a></li>\n" +
                "\n" +
                "<li class=\"zm-topic-cat-item\" data-id=\"388\"><a href=\"#摄影\">摄影</a></li>\n" +
                "\n" +
                "<li class=\"zm-topic-cat-item\" data-id=\"285\"><a href=\"#历史\">历史</a></li>\n" +
                "\n" +
                "<li class=\"zm-topic-cat-item\" data-id=\"686\"><a href=\"#文化\">文化</a></li>\n" +
                "\n" +
                "<li class=\"zm-topic-cat-item\" data-id=\"444\"><a href=\"#旅行\">旅行</a></li>\n" +
                "\n" +
                "<li class=\"zm-topic-cat-item\" data-id=\"1537\"><a href=\"#职业发展\">职业发展</a></li>\n" +
                "\n" +
                "<li class=\"zm-topic-cat-item\" data-id=\"3324\"><a href=\"#经济学\">经济学</a></li>\n" +
                "\n" +
                "<li class=\"zm-topic-cat-item\" data-id=\"2955\"><a href=\"#足球\">足球</a></li>\n" +
                "\n" +
                "<li class=\"zm-topic-cat-item\" data-id=\"4196\"><a href=\"#篮球\">篮球</a></li>\n" +
                "\n" +
                "<li class=\"zm-topic-cat-item\" data-id=\"395\"><a href=\"#投资\">投资</a></li>\n" +
                "\n" +
                "<li class=\"zm-topic-cat-item\" data-id=\"75\"><a href=\"#音乐\">音乐</a></li>\n" +
                "\n" +
                "<li class=\"zm-topic-cat-item\" data-id=\"68\"><a href=\"#电影\">电影</a></li>\n" +
                "\n" +
                "<li class=\"zm-topic-cat-item\" data-id=\"215\"><a href=\"#法律\">法律</a></li>\n" +
                "\n" +
                "<li class=\"zm-topic-cat-item\" data-id=\"1027\"><a href=\"#自然科学\">自然科学</a></li>\n" +
                "\n" +
                "<li class=\"zm-topic-cat-item\" data-id=\"445\"><a href=\"#设计\">设计</a></li>\n" +
                "\n" +
                "<li class=\"zm-topic-cat-item\" data-id=\"112\"><a href=\"#创业\">创业</a></li>\n" +
                "\n" +
                "<li class=\"zm-topic-cat-item\" data-id=\"237\"><a href=\"#健康\">健康</a></li>\n" +
                "\n" +
                "<li class=\"zm-topic-cat-item\" data-id=\"1740\"><a href=\"#商业\">商业</a></li>\n" +
                "\n" +
                "<li class=\"zm-topic-cat-item\" data-id=\"1538\"><a href=\"#体育\">体育</a></li>\n" +
                "\n" +
                "<li class=\"zm-topic-cat-item\" data-id=\"2143\"><a href=\"#科技\">科技</a></li>\n" +
                "\n" +
                "<li class=\"zm-topic-cat-item\" data-id=\"4217\"><a href=\"#化学\">化学</a></li>\n" +
                "\n" +
                "<li class=\"zm-topic-cat-item\" data-id=\"2253\"><a href=\"#物理学\">物理学</a></li>\n" +
                "\n" +
                "<li class=\"zm-topic-cat-item\" data-id=\"8437\"><a href=\"#生物学\">生物学</a></li>\n" +
                "\n" +
                "<li class=\"zm-topic-cat-item current\" data-id=\"19800\"><a href=\"#金融\">金融</a></li>\n" +
                "\n" +
                "</ul>";

        Document dc = Jsoup.parse(res);

        Elements elements = dc.select("ul>li");
        for (int i = 0; i < elements.size(); i++) {
            System.out.println(elements.get(i).attr("data-id"));
        }


    }

}
