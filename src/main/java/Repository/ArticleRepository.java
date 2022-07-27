package Repository;

import Entity.Article;
import Entity.User;
import Service.ApplicationConstant;
import org.w3c.dom.Text;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public static List<Article> allOfArticle() throws SQLException {
        List<Article> articleList = new ArrayList<>();
        String sql = "select * from article_table;";
        PreparedStatement ps = ApplicationConstant.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            articleList.add(new Article(rs.getLong(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getBoolean(7),
                    rs.getLong(8)));
        }
        Article article = new Article();
        if (article.isPublished()) {
            return articleList;
        }
        else return null;
    }

    public static Article selfArticleShow(User user) throws SQLException {
        Article article = new Article();
        String sql = "select TEXT from article_table where user_id = ?";
        PreparedStatement ps = ApplicationConstant.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            article.setUser_id(1, user.getId());
        } else {
            System.out.println("you haven't article");
        }
        return article;
    }

    public static void modifyText(Article articleText, User user) throws SQLException {
        String sql = "update article_table text = ? where user_id = ?";
        PreparedStatement ps = ApplicationConstant.getConnection().prepareStatement(sql);
        ps.setString(1, articleText.getText());
        ps.setLong(2, user.getId());
        int check = ps.executeUpdate();
        if (check == 1) {
            System.out.println(user.getUsername() + " text updated");
        }
    }

    public static int printArticleTitle(List<Article> articleList) {
        for (int i = 0; i < articleList.size(); i++) {
            Article article = articleList.get(i);
            System.out.println(article.getId() + "- " + article.getTitle());
        }
        System.out.print("Enter number of title that you want : ");
        int input = ApplicationConstant.getInput().nextInt();
        return input;
    }

    public static void addText(User user, Article article) throws SQLException {
        String sql = "insert into article_table insert into article_table" +
                "(article_title, text, content, create_date, isPublished, user_id)" +
                "values (?,?,?,to_date(?, 'yyy/mm/dd'),?,?)";
        PreparedStatement ps = ApplicationConstant.getConnection().prepareStatement(sql);
        ps.setString(1, article.getTitle());
        ps.setString(2, article.getText());
        ps.setString(3, article.getContent());
        ps.setString(4, article.getCreateDate());
        ps.setBoolean(5, article.isPublished());
        ps.setLong(6, user.getId());
        if (ps.getGeneratedKeys().next()) {
            article.setId(ps.getGeneratedKeys().getLong(1));
        }
        System.out.println("Dear " + user.getUsername() + " your " + article.getTitle() + " uploaded");
    }



}
