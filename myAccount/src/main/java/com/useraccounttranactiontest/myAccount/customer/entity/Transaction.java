package com.useraccounttranactiontest.myAccount.customer.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="TRANSACTIONS_TABLE")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="UID")
    private long id;


    @NonNull
    @Column(name="TRANSACTION_AMOUNT")
    private double amount;

    @NonNull
    @Column(name="TRANSACTION_DESC")
    private String Description;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CustomerId",referencedColumnName = "CUSTOMER_ID"),
            @JoinColumn(name="firstName", referencedColumnName="CUSTOMER_FIRST_NAME"),
            @JoinColumn(name="lastName",referencedColumnName = "CUSTOMER_LAST_NAME")
    })
    Customer customer;


    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", Description='" + Description + '\'' +
                '}';
    }
}
