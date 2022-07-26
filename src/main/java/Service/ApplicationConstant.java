package Service;

import Repository.ArticleRepository;
import Repository.UserRepository;
import Service.menu.UserMenu;

import java.sql.Connection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplicationConstant {
    public static final String[] USER_MENU= {"Login", "Sign up", "Exit"};
    public static UserMenu usermenu = new UserMenu();
    private static UserRepository userRepository= new UserRepository();
    private static Connection connection = new DBHelper().connect();
    public static Connection getConnection() {return connection;}
    public static Scanner input = new Scanner(System.in);
    public  static Scanner getInput(){return input;}
    public static UserRepository getUserRepository(){return userRepository;}
    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";
        Pattern p = Pattern.compile(regex);
        if (password == null) {
            return false;
        }
        Matcher m = p.matcher(password);
        return  m.matches();
    }
    private static ArticleRepository articleRepository = new ArticleRepository();

}
