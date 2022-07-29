package Service.menu;

import Entity.Article;
import Entity.User;
import Repository.ArticleRepository;
import Repository.UserRepository;
import Service.ApplicationConstant;
import Service.Printer;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static java.lang.System.exit;

public class UserMenu {

    User user = new User();

    public User runFirstMenu() throws SQLException, ParseException {
        System.out.println("WELCOME");
        while (true) {
            Printer.printFirstMenu(ApplicationConstant.USER_MENU);
            System.out.print("Enter your choice : ");
            int input = ApplicationConstant.getInput().nextInt();
            switch (input) {
                case 1:
                    user = login();
                    break;
                case 2:
                    signup();
                    break;
                case 3:
                    viewArticles();
                    break;
                case 4:
                    exit(1);
                    break;

            }

        }
    }

    public void runSecoundMenu() throws SQLException, ParseException {
        //System.out.println("WELCOME");
        while (true) {
            Printer.printFirstMenu(ApplicationConstant.USER_MENU);
            System.out.print("Enter your choice : ");
            int input = ApplicationConstant.getInput().nextInt();
            switch (input) {
                case 1:
                    UpdateUser(user);
                    break;
                case 2:
                    ViewYourArticles(user);
                    break;
                case 3:
                    viewArticles();
                    break;
                case 4:
                    ModifiedText(user);
                    break;
                case 5:
                    AddArticle(user);
                    break;
                case 6:
                    exit(1);

            }

        }
    }

    public static User login() throws SQLException {
        UserRepository userRepository = new UserRepository();
        User user;
        System.out.print("Enter your username for login : ");
        String inputUsername = ApplicationConstant.getInput().next();
        if (ApplicationConstant.getUserRepository().isUsernameExist(inputUsername)) {
            user = userRepository.returnIDUser(inputUsername);
            System.out.println(user.toString());
            System.out.println("Enter your password");
            String inputPassword = ApplicationConstant.getInput().next();


            if (inputPassword.equals(user.getPassword())) {
                System.out.println("you're log in");
                return user;
            }
        }
        return null;
    }


    public void UpdateUser(User user) throws SQLException {
        UserRepository userRepository = new UserRepository();
        System.out.print("Do you want change your password? y/n");
        char inputChar = ApplicationConstant.getInput().next().charAt(0);
        if (inputChar == 'y') {
            System.out.print("Enter Your new password");
            String password = ApplicationConstant.getInput().next();
            user.setPassword(password);
            userRepository.updateUser(user.getId(), user);
            System.out.println("UPDATED");
        } else exit(1);
    }

    public void ViewYourArticles(User user) throws SQLException {
        ArticleRepository articleRepository = new ArticleRepository();
        Article article = ArticleRepository.selfArticleShow(user);
        System.out.print(article.getTitle() + " : " + article.getText());
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


//        System.out.printFirstMenu("Enter your firstname : ");
//        String nationalCode = ApplicationConstant.getInput().next();
//        user.setNationalCode(nationalCode);
//        System.out.printFirstMenu("Enter your birth day (YYYY/MM/DD) : ");
//        String date = ApplicationConstant.getInput().next();
//        user.setBirthday(date);
    }

    public void viewArticles() throws SQLException {
        List<Article> articles = ArticleRepository.allOfArticle();
        for (int i = 0; i < ArticleRepository.allOfArticle().size(); i++) {
            Article article = articles.get(i);
            System.out.print(article.getId());
            System.out.print(" - " + article.getTitle());
        }
        System.out.print("now enter id for get this text : ");
        int input = ApplicationConstant.getInput().nextInt();
        Article articleChoose = articles.get(input);
        System.out.print(articleChoose.getText());
    }

    public void ModifiedText(User user) throws SQLException {
        System.out.print("Enter you modified article for update : ");
        String inputText = ApplicationConstant.getInput().next();
        ArticleRepository.modifyText(inputText, user);
    }

    public void AddArticle(User user) throws SQLException {
        Article article = new Article();
        System.out.print("Enter Date : (yyyy/mm/dd)");
        article.setCreateDate(ApplicationConstant.getInput().next());

        System.out.print("Enter your title of Article : ");
        article.setTitle(ApplicationConstant.getInput().next());

        System.out.print("Enter content of your Article : ");
        article.setContent(ApplicationConstant.getInput().next());

        System.out.println("Enter your Text : ");
        article.setText(ApplicationConstant.getInput().next());

        System.out.println("Do you want publish it? (y/n");
        char flag = ApplicationConstant.getInput().next().charAt(0);
        if(flag == 'y') {
            article.setPublished(true);
        }else article.setPublished(false);
        ArticleRepository.addText(user, article);
    }

}
