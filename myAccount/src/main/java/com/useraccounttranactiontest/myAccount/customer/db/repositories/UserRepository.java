package com.useraccounttranactiontest.myAccount.customer.db.repositories;


import com.useraccounttranactiontest.myAccount.customer.entity.Customer;
import com.useraccounttranactiontest.myAccount.customer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query(
            value = "SELECT COUNT(USER_ID) FROM USER_TABLE c WHERE c.USER_ID LIKE %?1%",
            nativeQuery = true)
    int isUserExistsByUserId(String userId);

    @Query(
            value = "SELECT * FROM USER_TABLE c WHERE c.USER_ID LIKE %?1%",
            nativeQuery = true)
    User getUserByUserId(String userId);

    @Query(
            value = "SELECT * FROM USER_TABLE ",
            nativeQuery = true)
    List<User> getAllUsers();
}
