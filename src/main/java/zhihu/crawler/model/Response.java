package zhihu.crawler.model;

import java.util.List;

/**
 * Created by huazi on 17/7/31.
 */
public class Response {
    int r;
    List<String> msg;

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public List<String> getMsg() {
        return msg;
    }

    public void setMsg(List<String> msg) {
        this.msg = msg;
    }
}
