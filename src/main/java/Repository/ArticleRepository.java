package Repository;

import Service.ApplicationConstant;

import java.sql.SQLException;
import java.sql.Statement;

public class ArticleRepository {
    public static void create_table() throws SQLException {
        String sql = "create table if not exists article_table(id serial primary key not null," +
                "article_name varchar(255) not null," +
                "article TEXT not null" +
                "user_id INTEGER REFRENCED user_table(id)," +
                "article_id Integer refrenced article_table(id)";
        Statement stm = ApplicationConstant.getConnection().createStatement();
        stm.executeUpdate(sql);
    }
}
