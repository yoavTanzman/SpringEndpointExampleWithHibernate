package com.useraccounttranactiontest.myAccount.customer.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="CUSTOMER_TABLE")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CID")
    private long id;

    @NonNull
    @Column(name="CUSTOMER_ID")
    private String customerId;

    @NonNull
    @Column(name="CUSTOMER_FIRST_NAME")
    private String firstName;

    @NonNull
    @Column(name="CUSTOMER_LAST_NAME")
    private String lastName;

    @NonNull
    @Column(name="CUSTOMER_BALANCE")
    private double balance;

    @OneToMany(
            targetEntity = Transaction.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy ="customer"
    )
    List<Transaction> transactionList;



    public void addTransaction(Transaction tempTransaction){
        if(transactionList == null ){
            transactionList = new ArrayList<>();
        }
        tempTransaction.setCustomer(this);
        transactionList.add(tempTransaction);
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", CustomerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", balance=" + balance +
                ", transactionList=" + transactionList +
                '}';
    }
}
