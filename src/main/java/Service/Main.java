package Service;

import Entity.User;
import Repository.UserRepository;

import java.sql.SQLException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws SQLException, ParseException {
        ApplicationConstant.getConnection();
        UserRepository.createTable();
        User user = new User(12, "sadasdas", "0054385150", "1999/4/4", "12345");

        UserRepository user1 = new UserRepository();
        user1.createdUser(user);
        user1.deleteUser(18);
    }
}
