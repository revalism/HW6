package Repository;

import Entity.Article;
import Entity.User;
import Service.ApplicationConstant;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ArticleRepository {
    public static void createTable() throws SQLException {
        String sql = "create table if not exists article_table(" +
                " id serial primary key not null," +
                " article_title varchar(255) not null," +
                " text TEXT not null," +
                "content varchar(1023), " +
                "create_date Date," +
                "isPublished bool ," +
                "user_id serial references user_table(id))";
        Statement stm = ApplicationConstant.getConnection().createStatement();
        stm.executeUpdate(sql);
    }

    public Article createArticle(Article article, User user) throws SQLException {
        String sql = "insert into article_table(article_title, text, content, create_date, isPublished, user_id)" +
                "values (?,?,?,to_date(?, 'yyy/mm/dd'),?,?)";
        PreparedStatement ps = ApplicationConstant.getConnection().prepareStatement(sql);
        ps.setString(1, article.getTitle());
        ps.setString(2, article.getBrief());
        ps.setString(3, article.getContent());
        ps.setString(4, article.getCreateDate());
        ps.setBoolean(5, article.isPublished());
        ps.setLong(6, user.getId());
        ps.executeUpdate();
        if (ps.getGeneratedKeys().next()) {
            article.setId(ps.getGeneratedKeys().getLong(1));
        }
        System.out.println("Dear " + user.getUsername() + " your " + article.getTitle() + " uploaded");
        return article;
    }

    //public Article view
}
