## How this program is designed
CSVReader class will read CSV file and transform CSV records in Transactions objects 
TransactionProcessor has core business logic where getBalance() method will calculate balance based upon from date, end date and account ID

## Business Logic
amount  will ne debited when given account number is from account
amount  will be credited  when given account number is to account
if any given transaction is present under reverse transaction then that transaction will be omitted even if it outside date range
please refer getBalance for detailed logic

## CSV file is in src/resource folder

##Main class  is MeBankApp
src/main/java/com/mebank/txn/MeBankApp

##Test class Details
To run test class , go to AppTest class in src/test/java/com/mebank/txn
This test class has all the scenarios mentioned in email.

## How to run this project?
src/main/java/com/mebank/txn/MeBankApp
in main method, sample parameters have been provided, one can just right click and run as MeBankApp class
or in target folder , JAR file is also available.
we can also use  below command to run the program
java -jar target/txn-analyser-1.0-SNAPSHOT.jar

