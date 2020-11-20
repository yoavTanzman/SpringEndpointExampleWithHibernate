package com.useraccounttranactiontest.myAccount.customer.db.repositories;



import com.useraccounttranactiontest.myAccount.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query(
            value = "SELECT COUNT(CUSTOMER_ID) FROM CUSTOMER_TABLE c WHERE c.CUSTOMER_ID LIKE %?1%",
            nativeQuery = true)
    int isUserExistsByUserId(String userId);

    @Query(
            value = "SELECT * FROM CUSTOMER_TABLE c WHERE c.CUSTOMER_ID LIKE %?1%",
            nativeQuery = true)
    Customer getUserByUserId(String userId);


}
