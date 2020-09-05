package com.example.assignment1.db;
import androidx.room.*;

import com.example.assignment1.User;

import java.util.List;


@Dao
public interface UserDAO {
    @Insert
    void insert(User... user);

    @Update
    void update(User... user);

    @Delete
    void delete(User user);

    @Query("SELECT COUNT(*) FROM " + AppDatabase.USER_TABLE)
    int userCount();

    @Query("SELECT COUNT(*) FROM " +AppDatabase.USER_TABLE+" WHERE userName=:user")
    int userNameCount(String user);

    @Query("SELECT * FROM "+ AppDatabase.USER_TABLE)
    List<User> getAllUser();

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE id = :userId")
    User getUserById(int userId);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE userName =:username")
    User getUserByUsername(String username);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE username=:inputName AND userPassword=:inputPass")
    List<User> getAuthenticatedUser(String inputName, String inputPass);
}
