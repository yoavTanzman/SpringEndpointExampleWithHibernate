package com.useraccounttranactiontest.myAccount;

import com.useraccounttranactiontest.myAccount.customer.db.dto.CustomerDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.useraccounttranactiontest.myAccount")
public class MyAccountApplication {



	public static void main(String[] args) {
		SpringApplication.run(MyAccountApplication.class, args);

	}

}
