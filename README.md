# SpringEndpointExampleWithHibernate


Hey,

below you may find information to the sample endpoints example , 

port is running on localhost:7074

embedded db used is h2 , you can see the results on 

http://localhost:7074/h2-console/login

use username :sa

no password 

|i have build up a mock list of users use them by sending request to 

http://localhost:7074/customer/init


<b>Project Stracture</b> 


<b>Controller</b><br>
    *CustomerController

<b>DB</b><br>
  <b>*configuration</b> <br>
         ** mapper_config<br>
  <b>*repository</b><br>
         ** CustomerRepository<br>
         ** UserRepository<br>
  <b>*entity</b><br>
        **Customer<br>
        **Transaction<br>
        **User<br>
        <br>
  <b>*service</b><br>
        **utils<br>
            ***transactionUtils<br>

  <b>*dto</b><br>
        **CustomerDto<br>
        **UserDto<br>
        **TransactionDto<br>
        
        
        
        
 <b>Endpoints:</b>
 
 GET http://localhost:7074/customer/init ::: init the list of users 
 
 GET http://localhost:7074/customer/hello :: check port connection expected results are status 200 and String = hello port 7074 
 
 POST http://localhost:7074/customer/add-current-new-customer :: add customer for exist user , Post string with 2 param customerId and initialCredit
 
 GET http://localhost:7074/customer/{customerId} get the customer information 
  
 POST http://localhost:7074/customer?customerId=???????? get the customer information  
 
 Please look at the screen shoots examples added to this repositories 
 
 
  
 
 <b>SetUp project </b>
 
 clone the code locally and run with an IDE of your choice I prefer Intellij 
 
 make sure you use java 1.8 
 
 next--> run the main class MyAccountApplicationTests.java 
 
 next open PostMan
 

<b>POSTMAN INSTRUCTIONS</b>

example run:  

step 1: GET http://localhost:7074/customer/hello check port connection 

step 2 :  GET http://localhost:7074/customer/init init the db

step 3:  POST http://localhost:7074/customer/add-current-new-customer 

in headers tab : key = content type , value = application/json 

inbody pick raw 

and paste the below json 

{
"customerId":"0000000001",
"initialCredit":"197.65"
}

step 4: GET http://localhost:7074/customer/0000000001


