package com.newswebsite.newswebsite.repository;

import com.newswebsite.newswebsite.bean.Administrator;
import com.newswebsite.newswebsite.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>{
    @Modifying
    @Transactional
    @Query(value = "insert into User(userID, name, phone, password) value(?1, ?2, ?3, ?4)", nativeQuery = true)
    void addUser(int userID, String name, String phone, String password);

    @Modifying
    @Transactional
    @Query(value = "update User u set u.name = ?2, u.phone = ?3, u.password = ?4 where u.userID = ?1")
    void updateUser(int userID, String name, String phone, String password);

    @Query(value = "select * from User a where a.name = ?1", nativeQuery = true)
    List<User> findByName(String name);

    @Query("select a from User a where a.phone = ?1")
    List<User> findByPhone(String phone);

    @Query("select a from User a where a.userID = ?1")
    List<User> queryByID(Integer id);

    @Query(value = "select * from User a limit ?1, ?2", nativeQuery = true)
    List<User> page(int p1, int p2);

    @Query("select a from Administrator a where a.name = ?1 and a.password = ?2")
    List<Administrator> login(String name, String password);

    @Modifying
    @Transactional
    @Query(value = "update User set userID = userID - 1 where userID > ?1", nativeQuery = true)
    void updateDelete(int id);
}
