package zhihu.crawler.model;

/**
 * Created by huazi on 17/7/31.
 */
public class TopicModel {
    String topicurl;
    int topcategory;
    String subcategory;


    public TopicModel(String topicurl, String subcategory) {
        this.topicurl = topicurl;
        this.subcategory = subcategory;
    }

    public TopicModel() {
    }

    public String getTopicurl() {
        return topicurl;
    }

    public void setTopicurl(String topicurl) {
        this.topicurl = topicurl;
    }

    public int getTopcategory() {
        return topcategory;
    }

    public TopicModel setTopcategory(int topcategory) {
        this.topcategory = topcategory;
        return this;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }
}
