package zhihu.crawler.model;


/**
 * Created by huazi on 17/7/31.
 */
public class SecondCategoryModel {

    private int firstCa;

    public static final String FIXEDPOSTURL = "https://www.zhihu.com/node/TopicsPlazzaListV2";

    private static final String HASH_ID = "a087fec1e0c6c7ee1533f01a94e0f9ee";

    public static int flag = -1;

    //{"topic_id":4217,"offset":60,"hash_id":"a087fec1e0c6c7ee1533f01a94e0f9ee"}
    public String getPostParam() {
        flag++;
        return new PostParam(firstCa, flag * 20, HASH_ID).toString();
    }

    class PostParam {
        private int topic_id;
        private int offset;
        private String hash_id;

        public PostParam(int topic_id, int offset, String hash_id) {
            this.topic_id = topic_id;
            this.offset = offset;
            this.hash_id = hash_id;
        }

        @Override
        public String toString() {
            return "{" +
                    "\"topic_id\":" + topic_id +
                    ",\"offset\":" + offset +
                    ",\"hash_id\":\"" + hash_id +
                    "\"}";
        }
    }

    public int getFirstCa() {
        return firstCa;
    }

    public void setFirstCa(int firstCa) {
        this.firstCa = firstCa;
    }
}
