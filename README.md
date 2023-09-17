# Divisible-Number-By-Range-Calculator

Web based backend application exposing REST Apis to determine the smallest positive number that can be divided by a
sequential range of numbers(eg. from 1 to 25) without remainder. 
Upper limit upto 100 can be provided as input to system.
The result can be fetched either in XMl or JSON format

Installation and Running Application:
- 
Instructions for running in Eclipse IDE:

	Download the zip then unzip or clone the Git repository.	 
	Open Command Prompt and Change directory to folder containing pom.xml.
	Open Eclipse
		File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip.
	Select the project and import. 
	Choose the Spring Boot Application file  (search for @SpringBootApplication) in project explorer.
	Right Click on the file and Run as Java Application.

Instructions for running from command line:

	Ensure Java 17 installed on system and JAVA_HOME correctly set.
	Ensure maven is installed on system.
	clone or download and extract the project Zip on local system.
	
	Build application- 
            Go to project directory where pom.xml located.
            build the project  ->mvn clean install
    Run Application-
            start spring boot app  -> mvnw spring:boot run
              or
            create jar - mvnw clean package  
            Run jar -   java -jar target\<app-generated-jar>.jar

Technologies used :
-
   Java 17
   Spring boot 3
   Swagger for API documentation. 
    
APIs:
-
Setting Upper Limit[POST]  - This is used to configure the Upper limit of the number range
                             Run with below:
                                curl -X POST http://localhost:8080/setupperlimit/15

Calculating Number [GET]-  Provides the resultant divisible number based on the limit set by previous API
                           output can be fetched as JSON or XML by providing Accept header 
                           Run with below:
                           
       curl -X GET http://localhost:8080/getdivisiblenumber -H "Accept: application/json"  							 		
       sample output: 
       {
         "message":"360360 is the smallest number that can be divided by 1 to 15",
         "calculationtime":"0 Milliseconds"
       }
      
       
       curl -X GET http://localhost:8080/getdivisiblenumber -H "Accept: application/xml" 
       
       sample output:  
       <CalculationResult>
            <message>360360 is the smallest number that can be divided by 1 to 15</message>
            <calculationtime>0 Milliseconds</calculationtime>
       </CalculationResult>
                                                                      

More details of the API can be retrieved using swagger URl -http://localhost:8080/swagger-ui/index.html


   
     
                              