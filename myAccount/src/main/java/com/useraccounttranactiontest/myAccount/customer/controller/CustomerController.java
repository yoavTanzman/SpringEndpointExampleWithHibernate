package com.useraccounttranactiontest.myAccount.customer.controller;


import com.useraccounttranactiontest.myAccount.customer.db.dto.CustomerDto;
import com.useraccounttranactiontest.myAccount.customer.db.dto.TransactionDto;
import com.useraccounttranactiontest.myAccount.customer.db.dto.UserDto;
import com.useraccounttranactiontest.myAccount.customer.db.repositories.CustomerRepository;
import com.useraccounttranactiontest.myAccount.customer.db.repositories.UserRepository;
import com.useraccounttranactiontest.myAccount.customer.entity.Customer;
import com.useraccounttranactiontest.myAccount.customer.entity.Transaction;
import com.useraccounttranactiontest.myAccount.customer.entity.User;
import com.useraccounttranactiontest.myAccount.customer.service.utils.transactionsUtils;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    UserRepository userRepository;

    /**
     *
     * Run health check to see connction to the port
     * @return
     */

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(path="/hello",produces = "application/json")
    public ResponseEntity<?> testPort(){
        return new ResponseEntity<>("Hello port 7074", HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(path="/add-current-new-customer",produces = "application/json")
    public ResponseEntity<?> addAccountToCurrentClient(@RequestBody String json){
        JSONObject jsonObject = new JSONObject(json);
        String customerId = jsonObject.getString("customerId");
        double initialCredit = Double.parseDouble(jsonObject.getString("initialCredit"));
        if(userRepository.isUserExistsByUserId(customerId)==0){
            return new ResponseEntity<>("User not exists",HttpStatus.BAD_REQUEST);
        }
        User userTemp = userRepository.getUserByUserId(customerId);
        CustomerDto customerDto = new CustomerDto(
                userTemp.getUserId(),
                userTemp.getFirstName(),
                userTemp.getLastName(),
                initialCredit);
        Customer customerEntity = modelMapper.map(customerDto,Customer.class);

        if(initialCredit>0){
            Transaction autoTransaction = addTransactionAutomatically(initialCredit);
            double currentBalance= initialCredit-autoTransaction.getAmount();
            customerEntity.addTransaction(autoTransaction);
            customerEntity.setBalance(currentBalance);
        }
        customerRepository.save(customerEntity);
        return new ResponseEntity<>(customerEntity.toString(),HttpStatus.OK);
    }

    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @GetMapping(path="/init",produces = "application/json")
    public ResponseEntity<?> initExample(){
        List<UserDto> userList=new ArrayList<>();
        userList.add(new UserDto("0000000001","Test","User"));
        userList.add(new UserDto("0000000002","Test1","User1"));
        userList.add(new UserDto("0000000003","Test2","User2"));
        userList.add(new UserDto("0000000004","Test3","User3"));
        userList.add(new UserDto("0000000005","Test4","User4"));
        for(UserDto userDto:userList){
            User userEntity = modelMapper.map(userDto, User.class);
            userRepository.save(userEntity);
        }
        return new ResponseEntity<>("Init Db was successfully created",HttpStatus.OK);
    }


    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @PostMapping(path="/add-user",produces = "application/json")
    public ResponseEntity<?> addUser(@RequestBody String userJson){
        JSONObject jsonObject = new JSONObject(userJson);
        String firstName = jsonObject.getString("firstName");
        String lastName = jsonObject.getString("sureName");
        String socialSecurity = jsonObject.getString("userId");
        UserDto userDto = new UserDto(socialSecurity,firstName,lastName);
        User userEntity = modelMapper.map(userDto, User.class);
        userRepository.save(userEntity);


        return new ResponseEntity<>("user added: "+userEntity,HttpStatus.OK);

    }

    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @GetMapping(path="/get-all-users",produces = "application/json")
    public ResponseEntity<?> getAll(){

        List<User> userList = new ArrayList<>();
        userList = userRepository.getAllUsers();
        return new ResponseEntity<>(userList,HttpStatus.OK);

    }
    /**
     *
     * end point to add new customer request json with the below param syntax is important
     * firstName
     * sureName
     * userId
     * balance
     * @param customer
     * @return
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(path="add-new-customer",produces = "application/json")
    public ResponseEntity<?> addNewCustomer(@RequestBody String customer){

        JSONObject jsonObject = new JSONObject(customer);
        String firstName = jsonObject.getString("firstName");
        String lastName = jsonObject.getString("sureName");
        String socialSecurity = jsonObject.getString("userId");
        double balance = Double.parseDouble(jsonObject.getString("balance"));

        if(customerRepository.isUserExistsByUserId(socialSecurity)!=0){
            return new ResponseEntity<>("User already exists",HttpStatus.BAD_REQUEST);
        }
        CustomerDto customerDto = new CustomerDto(socialSecurity,firstName,lastName,balance);
        Customer customerEntity = modelMapper.map(customerDto,Customer.class);
        customerRepository.save(customerEntity);
        return new ResponseEntity<>("customer added: "+customerEntity.toString(),HttpStatus.OK);
    }

    /**
     * per your request this end point will return the customer information \
     * please note we have to send it as a param to the endpoint
     * @param customerId
     * @return
     */
    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @GetMapping(path="/{customerId}",produces = "application/json")
    public ResponseEntity<?> fetchUserById(@PathVariable("customerId") String customerId ) {

        if(customerRepository.isUserExistsByUserId(customerId)==0){
            return new ResponseEntity<>("Customer Not Found",HttpStatus.BAD_REQUEST);
        }
        Customer tempCustomer = customerRepository.getUserByUserId(customerId);
        return new ResponseEntity<>(tempCustomer.toString(),HttpStatus.OK);

    }

    /**
     * A different way to fetch it chose whatever you find comfortable
     * @param customerId
     * @return
     */

    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @PostMapping(path="",produces = "application/json")
    public ResponseEntity<?> fetchUserByIdWithURLVariable(@RequestParam("customerId") String customerId ) {

        if(customerRepository.isUserExistsByUserId(customerId)==0){
            return new ResponseEntity<>("Customer Not Found",HttpStatus.BAD_REQUEST);
        }
        Customer tempCustomer = customerRepository.getUserByUserId(customerId);
        return new ResponseEntity<>(tempCustomer.toString(),HttpStatus.OK);

    }

    /**
     * endpoint to add transaction please note the json field, they must be accurate
     * all Strings
     * customerId
     * amount
     * description
     * @param transactionJson
     * @return
     */
    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @PostMapping(path="/add-transaction",produces = "application/json")
    public ResponseEntity<?> addTransaction(@RequestBody  String transactionJson ) {
        JSONObject jsonObject = new JSONObject(transactionJson);
        String customerId = jsonObject.getString("customerId");
        double amount = Double.parseDouble(jsonObject.getString("amount"));
        String description =jsonObject.getString("description");
        if(customerRepository.isUserExistsByUserId(customerId)==0){
            return new ResponseEntity<>("Customer Not Found",HttpStatus.BAD_REQUEST);
        }
        Customer tempCustomer = customerRepository.getUserByUserId(customerId);
        TransactionDto transactionDto = new TransactionDto(description,amount);
        Transaction transaction = modelMapper.map(transactionDto,Transaction.class);
        tempCustomer.addTransaction(transaction);
        customerRepository.save(tempCustomer);
        return new ResponseEntity<>("Transaction was added ",HttpStatus.OK);
    }

    private Transaction addTransactionAutomatically(double initialCredit){
        double randomAmountTransaction = transactionsUtils.getRandomTransactionAmount(initialCredit);
        String randomDes = transactionsUtils.generateRandomDescription();
        TransactionDto transactionDto = new TransactionDto(randomDes,randomAmountTransaction);
        Transaction transaction = modelMapper.map(transactionDto,Transaction.class);
        return transaction;
    }

}
