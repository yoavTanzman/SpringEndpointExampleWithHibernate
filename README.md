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


Project Stracture 

DB
  *configuration 
         * mapper_config
  *repository
         * CustomerRepository
         * UserRepository
  *entity
        *Customer
        *Transaction
        *User
        
  *service
        *utils
            *transactionUtils

  *dto
        *CustomerDto
        *UserDto
        *TransactionDto
        
        
        
        
 Endpoints:
 
 GET http://localhost:7074/customer/init ::: init the list of users 
 
 GET http://localhost:7074/customer/hello :: check port connection expected results are status 200 and String = hello port 7074 
 
 POST http://localhost:7074/customer/add-current-new-customer :: add customer for exist user , Post string with 2 param customerId and initialCredit
 
 GET http://localhost:7074/customer/{customerId} get the customer information 
  
 POST http://localhost:7074/customer?customerId=???????? get the customer information  
 
 Please look at the screen shoots examples added to this repositories 
 
 
  
 
 
 

to run with PostMan
