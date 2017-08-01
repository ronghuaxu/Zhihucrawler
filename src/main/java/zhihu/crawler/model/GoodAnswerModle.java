package zhihu.crawler.model;

/**
 * Created by huazi on 17/8/1.
 */
public class GoodAnswerModle {

    private int upagree;
    private String questiontitle;
    private String subcategory;
    private String besturl;

    public int getUpagree() {
        return upagree;
    }

    public void setUpagree(int upagree) {
        this.upagree = upagree;
    }

    public String getQuestiontitle() {
        return questiontitle;
    }

    public void setQuestiontitle(String questiontitle) {
        this.questiontitle = questiontitle;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getBesturl() {
        return besturl;
    }

    public void setBesturl(String besturl) {
        this.besturl = besturl;
    }
}
