package com.useraccounttranactiontest.myAccount.customer.db.dto;

import lombok.*;
import org.springframework.stereotype.Component;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Component
public class CustomerDto {


    @NonNull
    private String userId;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    private double balance;
}
