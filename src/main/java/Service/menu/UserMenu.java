package Service.menu;

import Entity.User;
import Service.ApplicationConstant;
import Service.Printer;
import org.w3c.dom.ls.LSOutput;

import java.sql.SQLException;

public class UserMenu {
    public void runFirstMenu() throws SQLException {
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
        String inputUsername = ApplicationConstant.getInput().nextLine();
        if(ApplicationConstant.getUserRepository().isUsernameExist(inputUsername)){
            usernameIsCorrect = true;
            if (!user.isFirstLogin()) {
                System.out.print("Enter your national code : ");
                while (true) {
                    System.out.print("Enter your password for set : ");
                    String inputPassword1 = ApplicationConstant.getInput().nextLine();
                    if (ApplicationConstant.isValidPassword(inputPassword1)) {
                        System.out.print("Again enter your password for set : ");
                        String inputPassword2 = ApplicationConstant.getInput().nextLine();
                        if (inputPassword1.equals(inputPassword2)) {
                            user.setPassword(inputPassword1);
                            user.setFirstLogin(true);
                        }
                        break;
                    }  else {
                        System.out.println("your password not enough strong try again");
                    }
                }
            }
        }
        System.out.print("Enter your password for login : ");
        String password = ApplicationConstant.getInput().nextLine();
        if(user.getPassword().equals(password) && usernameIsCorrect){
            System.out.println("Welcome");
        }else {
            System.out.println("your username or password isn't correct");
        }
    }
    public void signup() throws SQLException {
        User user = new User();
        String username;
        while (true){
            System.out.print("Enter username : ");
            username = ApplicationConstant.getInput().nextLine();
            if(ApplicationConstant.getUserRepository().isUsernameExist(username)){
                continue;
            }else {
                user.setUsername(username);
                break;
            }
        }
        System.out.print("Enter your firstname : ");
        String nationalCode = ApplicationConstant.getInput().next();
        user.setNationalCode(nationalCode);
        System.out.print("Enter your birth day (YYYY/MM/DD) : ");
        String date = ApplicationConstant.getInput().next();
        user.setBirthday(date);
    }

    public void viewArticles(){
        User user = new User();
    }
}
