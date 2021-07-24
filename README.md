# local-sec

This is a challenge project that has an User as it's main Entity. The user may have several calculated verifying digits tied to him. 

This project will calculate the digits and save them both to an in-memory database and an in-memory cache as well. 
If the pair of number and verifying digit is already present in the cache it will not be calculated again. 

The user may have his name and email encrypted with RSA 2048. 

This project contains JUnit tests and you'll find a postman_collection file at the project root which can be inported into Postman and have all the APIs tested as well. 

# Running the application
To run the application download and unpack it, then open a command window on the root of the project and run the following command:
  
  `mvn spring-boot:run`

Now you should see a message similar to this: `Tomcat started on port(s): 8080 (http) with context path '/inter'`

# Running the tests
To run the JUnit tests, go to the root of the project and run the following command:

`mvn -Dtest="com.inter.challenge.**" test`

Or you could go to the package inside your favorite IDE (after importing the project) and right-clicking it and then select the following: `Run 'Tests in 'com.inter.challenge''` (IntelliJ IDEA)

# Requesting
The project APIs will respond to requests sent to `localhost:8080/inter/...`

We have two controllers available: `/user` and `/digit`.

# Postman
You may import the `postman_collection.json` found in the root folder into Postman, there you'll find more details on how to call APIs and run Postman tests as well.


This has been developed by me throughout July 2021.
