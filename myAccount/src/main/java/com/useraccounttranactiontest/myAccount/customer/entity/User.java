package com.useraccounttranactiontest.myAccount.customer.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="USER_TABLE")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="UID")
    private long id;


    @NonNull
    @Column(name="USER_ID")
    private String userId;

    @NonNull
    @Column(name="USER_FIRST_NAME")
    private String firstName;

    @NonNull
    @Column(name="USER_LAST_NAME")
    private String lastName;


}
