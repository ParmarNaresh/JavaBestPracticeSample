package com.nareshparmar.javabestpracticesample.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.nareshparmar.javabestpracticesample.model.User;

import java.util.List;
@Dao
public interface UserDao {

    @Query("SELECT * FROM User")
    List<User> getAll();

    @Query("SELECT * FROM User WHERE userId IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM User WHERE email= :email AND " +
            "password LIKE :password LIMIT 1")
    User verifyUser(String email, String password);

    @Query("SELECT * FROM User WHERE email= :email LIMIT 1")
    User checkEmailExiest(String email);

    @Insert
    void insertAll(User... users);

    @Insert
    long insertUser(User user);

    @Delete
    void delete(User user);

}
