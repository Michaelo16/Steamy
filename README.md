# Steamy
Steamy Application

## Requirements
Java JDK 17, I created the project in Eclipse. 

## Dependencies
This project uses JavaFX 22 ad PostgreSQL JDBC 42.7.3 Drivers which can be found at: 
https://gluonhq.com/products/javafx/,
https://jdbc.postgresql.org/download/

## Setup Instructions
1. Clone the repository
2. Import the src folder into a new project
3. add the library files to the project build path
4. Navigate to the common package
5. add these vm arguments to the runtime config replacing the file path with your own
--module-path "path/to/your/javafx" --add-modules javafx.controls,javafx.graphics,javafx.fxml

## Running the Project
Run SteamyApp.java 