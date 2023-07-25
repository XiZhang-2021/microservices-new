package org.programming.dao;

import jakarta.transaction.Transactional;
import org.programming.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    @Modifying
    @Transactional
    @Query("delete from User u where u.id =:uid")
    void deleteByUserid(@Param("uid") int uid);
}
