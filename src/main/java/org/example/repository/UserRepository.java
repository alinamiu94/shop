package org.example.repository;

import org.example.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserRepository extends Repository {

    private static final String SELECT_FROM_USER = "select * from app_user;";
    private static final String SELECT_USER_BY_ID_QUERY = "select * from app_user where id = %s;";
    private static final String DELETE_USER_BY_ID = "delete from app_user where id = %s;";
    private static final String SELECT_USER_BY_NAME_QUERY = "select * from app_user where name = '%s';";
    private static final String INSERT_USER = "INSERT INTO APP_USER(id,NAME,password,created_at) " +
            "values (nextval('user_seq'), '%s','%s',to_timestamp('%s', 'dd-mm-yyyy'));";


    public List<User> findAllUsers() {
        try(Connection conn = connect()){
            PreparedStatement statement = conn.prepareStatement(SELECT_FROM_USER);
            ResultSet resultSet = statement.executeQuery();

            List<User> userList = new ArrayList<>();

            while(resultSet.next()) {
                int userId = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String userPassword = resultSet.getString(3);
                Date dueDate = resultSet.getDate(4);

                User user = new User(userId,name, userPassword,dueDate);
                userList.add(user);

            }
            return userList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public User getUserById(int id) {
        try(Connection conn = connect()){
            PreparedStatement statement = conn.prepareStatement(String.format(SELECT_USER_BY_ID_QUERY,id));
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()) {
                return null;
            }

            int userId = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String userPassword = resultSet.getString(3);
            Date dueDate = resultSet.getDate(4);


            return new User(userId,name,userPassword,dueDate);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void deleteUser(int id) {

        try(Connection conn = connect()){
            PreparedStatement statement = conn.prepareStatement(String.format(DELETE_USER_BY_ID,id));
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void addUser(User user){

        try(Connection conn = connect()){
            PreparedStatement statement = conn.prepareStatement(String.format(INSERT_USER,
                    user.getName(), BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()),getDateInValidFormat(user.getCreatedAt())));
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public String getDateInValidFormat(Date date) {
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    public User findUserByUserName(String username) {
        try (Connection conn = connect()) {
            PreparedStatement statement = conn.prepareStatement(String.format(SELECT_USER_BY_NAME_QUERY, username));
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }



            int userId = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String userPassword = resultSet.getString(3);
            Date dueDate = resultSet.getDate(4);


            return new User(userId, name, userPassword, dueDate);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
