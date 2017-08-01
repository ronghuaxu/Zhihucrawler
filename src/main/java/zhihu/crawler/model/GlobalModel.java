package zhihu.crawler.model;

import zhihu.crawler.utils.Config;

/**
 * Created by huazi on 17/8/1.
 */
public class GlobalModel {
    //阈值  设置为5000，意思代表爬取点赞数为5000以上的精彩回答
    public static final int THRESHOLD = Config.getInt("threshold.value");
}
