package com.cyphersoft.osahaneat.repository;

import com.cyphersoft.osahaneat.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {

    List<Users> findByUserNameAndPassword(String username, String password);
    Users findByUserName(String userName);

}
