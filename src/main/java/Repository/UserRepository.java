package Repository;

import Entity.User;
import Service.ApplicationConstant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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

    public User createdUser(User user) throws SQLException, ParseException {
        if (!isUsernameExist(user.getUsername())) {
            String sql = "insert into user_table(username, nationalCode, birthday, password)values (?,?,to_date(?,'yyy/mm/dd'),?)";
            PreparedStatement ps = ApplicationConstant.getConnection().prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getNationalCode());
            ps.setString(3, user.getBirthday());
            ps.setString(4, user.getPassword());
            ps.executeUpdate();
            if (ps.getGeneratedKeys().next()) {
                user.setId(ps.getGeneratedKeys().getLong(1));
            }
            System.out.println("user : " + user.getUsername() + " created successfully.");
        }
        return user;
    }

    public boolean isUsernameExist(String username) throws SQLException {
        String sql = "select * from user_table where username = ?";
        PreparedStatement ps = ApplicationConstant.getConnection().prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            System.out.println("\"" + username + "\" already exist");
            return false;
        }
    }

    public User deleteUser(long id) throws SQLException {
        User deletedUser = foundById(id);
        String sql = "delete from user_table where id = ?";
        PreparedStatement ps = ApplicationConstant.getConnection().prepareStatement(sql);
        ps.setLong(1, id);
        int check = ps.executeUpdate();
        if (check == 1) {
            System.out.println("\"" + deletedUser.getUsername() + "\" user are deleted");
            return deleteUser(id);
        } else {
            return null;
        }
    }

    public User foundById(long id) throws SQLException {
        User foundById = new User();
        String sql = "select * from user_table where id = ?";
        PreparedStatement ps = ApplicationConstant.getConnection().prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            foundById.setId(rs.getLong(1));
            foundById.setUsername(rs.getString(2));
            foundById.setNationalCode(rs.getString(3));
            foundById.setBirthday(rs.getString(4));
            foundById.setPassword(rs.getString(5));
        }
        return foundById;

    }

    public User setPassword(User setUserPassword) {
        return null;
    }

    public void updateUser(long id, User user) throws SQLException {
        String sql = "update user_table username = ?, nationalcode = ?, birthday = ?, password = ?";
        PreparedStatement ps = ApplicationConstant.getConnection().prepareStatement(sql);
        ps.setLong(1, user.getId());
        ps.setString(2, user.getUsername());
        ps.setString(3, user.getNationalCode());
        ps.setString(4, user.getBirthday());
        ps.setString(5, user.getPassword());
        int check = ps.executeUpdate();
        if (check == 1) {
            System.out.println("\"" + foundById(id).getUsername() + "\" updated");
        }
    }

    public List<User> findAllUser(String username) throws SQLException {
        List<User> allUsers = new ArrayList<>();
        String sql = "select * from user_table";
        PreparedStatement ps = ApplicationConstant.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            while (rs.next()) {
                allUsers.add(new User(rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)));
            }
        }else{
            System.out.println("user not exist");
        }
        return allUsers;

    }

    public User foundByUsername(String username) throws SQLException {
        User foundByUser = new User();
        String sql = "select * from user_table where username = ?";
        PreparedStatement ps = ApplicationConstant.getConnection().prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            foundByUser.setId(rs.getLong(1));
            foundByUser.setUsername(rs.getString(2));
            foundByUser.setNationalCode(rs.getString(3));
            foundByUser.setBirthday(rs.getString(4));
            foundByUser.setPassword(rs.getString(5));

        }
        return foundByUser;
    }
}

