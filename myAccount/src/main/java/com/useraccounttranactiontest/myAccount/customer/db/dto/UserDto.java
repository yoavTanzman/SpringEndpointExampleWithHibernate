package com.useraccounttranactiontest.myAccount.customer.db.dto;


import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Component
public class UserDto {

    @NonNull
    private String userId;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;




}
