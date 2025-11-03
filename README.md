Complete Banking System (Plain Java + JDBC + MySQL)
=================================================

Structure: follows your requested layout (models, dao/interfaces, dao/impl, service, util, ui, main)

Setup:
1. Run src/main/resources/schema.sql in your MySQL server to create database and tables.
2. Update src/main/resources/application.properties with your MySQL credentials.
3. Build: mvn clean package
4. Run: java -cp target/bankingsystem-1.0-SNAPSHOT.jar org.example.bankingsystem.main.BankingApp
   Or run from your IDE by running org.example.bankingsystem.main.BankingApp

Notes:
- UI classes are placeholders (console-based minimal). Use services in main or UI pages to interact with DB.
- Tests use Mockito and H2 (where appropriate) and can be run with mvn test.
- DAOs include basic CRUD + exception handling + logging.

