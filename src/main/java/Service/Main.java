package Service;

import Entity.Article;
import Entity.User;
import Repository.ArticleRepository;
import Repository.UserRepository;
import Service.menu.UserMenu;

import java.sql.SQLException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws SQLException, ParseException {
//        ArticleRepository.createTable();
////        ApplicationConstant.getConnection();
////        UserRepository.createTable();
//        User user = new User(1, "sadasdas", "0054385150", "1999/4/4", "12345");
////        ApplicationConstant.getUserRepository().createdUser(user);
//////
////        UserRepository user1 = new UserRepository();
////        user1.createdUser(user);
////        user1.deleteUser(18);
//        ArticleRepository.createTable();
////long x = 2;
//        Article article = new Article(1, "dsdsd", "dsds","helllloooo", "sksk", "1999/2/2", true, user.getId() );
//        ArticleRepository articleRepository = new ArticleRepository();
//        articleRepository.createArticle(article, user);
        UserMenu userMenu = new UserMenu();
        userMenu.runFirstMenu();
    }
}
