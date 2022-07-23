package Repository;

import Entity.User;
import Service.ApplicationConstant;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UserRepository {
    public static void createTable() throws SQLException {
        String sql = "create table if not exists user_table(" +
                " id serial primary key not null," +
                " username varchar(255) unique not null," +
                " nationalCode varchar(15)," +
                " birthday Date not null," +
                " password varchar(255) not null)";
        Statement stm = ApplicationConstant.getConnection().createStatement();
        stm.executeUpdate(sql);
    }

    public static User createUser(User userCreated) throws SQLException, ParseException {
        String sql = "insert into user_table(username, nationalCode, birthday, password)values (?,?,to_date(?,'yyy/mm/dd'),?)";
        PreparedStatement ps = ApplicationConstant.getConnection().prepareStatement(sql);
        ;
        ps.setString(1, userCreated.getUsername());
        ps.setString(2, userCreated.getNationalCode());
        ps.setString(3, userCreated.getBirthday());
        ps.setString(4, userCreated.getPassword());
        ps.executeUpdate();
        if (ps.getGeneratedKeys().next()) {
            userCreated.setId(ps.getGeneratedKeys().getLong(1));
        }
        System.out.println("userCreated : " + userCreated.getUsername() + " created successfully.");
        return userCreated;
    }

}

