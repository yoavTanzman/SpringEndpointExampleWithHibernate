package com.useraccounttranactiontest.myAccount.customer.db.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Component
public class TransactionDto {

    private String desc;
    private double amount;


}
