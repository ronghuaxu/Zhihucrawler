package zhihu.crawler.db;

import zhihu.crawler.model.GoodAnswerModle;
import zhihu.crawler.model.TopicModel;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huazi on 17/7/31.
 */
public class DBinstore {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public int insert(TopicModel topicModel) {

        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int i = 0;
        String sql = "insert into zhihu (topic_url,top_category,sub_category) values(?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, topicModel.getTopicurl());
            pstmt.setInt(2, topicModel.getTopcategory());
            pstmt.setString(3, topicModel.getSubcategory());
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public boolean isexitsGoodAnswer(GoodAnswerModle goodAnswerModle) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "select * from zhihu_useful where besturl =?";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, goodAnswerModle.getBesturl());
            ResultSet rs = pstmt.executeQuery();
            boolean flag = rs.next();

            pstmt.close();
            conn.close();
            if (flag) {
                return true;
            } else
                return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public int insertGoodAnswer(GoodAnswerModle goodAnswerModle) {
        if (isexitsGoodAnswer(goodAnswerModle)) {
            return 0;
        }

        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int i = 0;
        String sql = "insert into zhihu_useful (upagree,questiontitle,subcategory,besturl) values(?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setInt(1, goodAnswerModle.getUpagree());

            pstmt.setString(2, goodAnswerModle.getQuestiontitle());
            pstmt.setString(3, goodAnswerModle.getSubcategory());
            pstmt.setString(4, goodAnswerModle.getBesturl());
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }


    public List<TopicModel> getAll(int topcategory) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<TopicModel> topicModelList = new ArrayList<>();
        int i = 0;
        String sql = "select * from zhihu where top_category =?";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setInt(1, topcategory);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                TopicModel topicModel = new TopicModel();
                topicModel.setTopicurl(rs.getString("topic_url"));
                topicModel.setTopcategory(rs.getInt("top_category"));
                topicModel.setSubcategory(rs.getString("sub_category"));
                topicModelList.add(topicModel);
            }
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return topicModelList;
    }

}
