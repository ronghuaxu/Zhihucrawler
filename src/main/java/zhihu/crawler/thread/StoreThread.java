package zhihu.crawler.thread;

import zhihu.crawler.db.DBinstore;
import zhihu.crawler.model.TopicModel;

/**
 * Created by huazi on 17/7/31.
 */
public class StoreThread extends Thread {
    private TopicModel topicModel;
    private DBinstore dBinstore;


    public StoreThread(TopicModel topicModel, DBinstore dBinstore) {
        this.topicModel = topicModel;
        this.dBinstore = dBinstore;
    }

    @Override
    public void run() {
        dBinstore.insert(topicModel);
    }
}

