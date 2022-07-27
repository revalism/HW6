package Service.menu;

import Entity.Article;
import Entity.User;
import Repository.ArticleRepository;
import Repository.UserRepository;
import Service.ApplicationConstant;
import Service.Printer;
import org.w3c.dom.ls.LSOutput;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;

public class UserMenu {
    public void runFirstMenu() throws SQLException, ParseException {
        System.out.println("WELCOME");
        while (true) {
            Printer.print(ApplicationConstant.USER_MENU);
            System.out.print("Enter your choice : ");
            int input = ApplicationConstant.getInput().nextInt();
            switch (input) {
                case 1:
                    login();
                    break;
                case 2:
                    signup();
                    break;
                case 3:
                    viewArticles();
                    break;

            }

        }
    }

    public void login() throws SQLException {
        boolean usernameIsCorrect = false;
        User user = new User();
        System.out.print("Enter your username for login : ");
        String inputUsername = ApplicationConstant.getInput().next();
        if (ApplicationConstant.getUserRepository().isUsernameExist(inputUsername)) {
            usernameIsCorrect = true;
            if (!user.isFirstLogin()) {
                System.out.print("Enter your national code : ");
                while (true) {
                    System.out.print("Enter your password for set : ");
                    String inputPassword1 = ApplicationConstant.getInput().next();
                    if (ApplicationConstant.isValidPassword(inputPassword1)) {
                        System.out.print("Again enter your password for set : ");
                        String inputPassword2 = ApplicationConstant.getInput().nextLine();
                        if (inputPassword1.equals(inputPassword2)) {
                            user.setPassword(inputPassword1);
                            user.setFirstLogin(true);
                        }
                        break;
                    } else {
                        System.out.println("your password not enough strong try again");
                    }
                }
            }
        }
        System.out.print("Enter your password for login : ");
        String password = ApplicationConstant.getInput().nextLine();
        if (user.getPassword().equals(password) && usernameIsCorrect) {
            System.out.println("Welcome");
        } else {
            System.out.println("your username or password isn't correct");
        }
    }

    public void afterLogin(User user) {

    }

    public void signup() throws SQLException, ParseException {
        User user = new User();
        UserRepository userRepository = new UserRepository();

        while (true) {
            System.out.print("Enter username : ");
            String username = ApplicationConstant.getInput().next();
            if (!ApplicationConstant.getUserRepository().isUsernameExist(username)) {
                user.setUsername(username);
                System.out.println();
                System.out.print("Enter your national code : ");
                String nationalCode = ApplicationConstant.getInput().next();
                user.setNationalCode(nationalCode);
                user.setPassword(nationalCode);
                System.out.print("Enter your birthday (yyy/mm/dd) : ");
                String date = ApplicationConstant.getInput().next();
                user.setBirthday(date);
                userRepository.createdUser(user);

                break;
            } else {
                System.out.println("this username is exist");
                continue;
            }

        }


//        System.out.print("Enter your firstname : ");
//        String nationalCode = ApplicationConstant.getInput().next();
//        user.setNationalCode(nationalCode);
//        System.out.print("Enter your birth day (YYYY/MM/DD) : ");
//        String date = ApplicationConstant.getInput().next();
//        user.setBirthday(date);
    }

    public void viewArticles() throws SQLException {
        List<Article> articles = ArticleRepository.allOfArticle();
        for (int i = 0; i < ArticleRepository.allOfArticle().size(); i++) {
            Article article = articles.get(i);
            System.out.print(article.getId());
            System.out.println(" - " + article.getTitle());
        }
        System.out.print("now enter id for get this text : ");
        int input = ApplicationConstant.getInput().nextInt();
        Article articleChoose = articles.get(input);
        System.out.println(articleChoose.getText());
    }
}
