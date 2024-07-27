# Steamy
Steamy Application

## Description
--
A small JavaFX application I made as a final project in my Database Theory and Practice class. It aims to re-create 
the features and implementation of Valve's Steam. Current implemented features include:
Creating accounts and logging in,
password encryption,
a functional store that includes different fake games
searching by title, genre, popularity, relevance among friends. 
The ability to 'purchase' games and add them to your library,
the ability to check your profile page and view your friends,
and view other friend's profile pages

There were a lot of features that I never got around to finishing and as a result there are some remnants in the database and code of their existence. 
I have included the PostgreSQL Dump file for the database

## Requirements
Java JDK 17, I created the project in Eclipse. 

## Dependencies
This project uses JavaFX 22 and PostgreSQL JDBC 42.7.3 Drivers which can be found at: 
https://gluonhq.com/products/javafx/,
https://jdbc.postgresql.org/download/

## Setup Instructions
1. Clone the repository
2. Import the src folder into a new project
3. download the required libraries and add them to the project buildpath
4. Navigate to the application package
5. add these vm arguments to the runtime config for the main SteamyApp.java, replacing the file path with your own
--module-path "path/to/your/javafx/lib" --add-modules javafx.controls,javafx.graphics,javafx.fxml

## Running the Project
Run SteamyApp.java

The test user that I have been using is Username: alicewonder   password: wonderland123
if you want to search for games most of the test games start with the word 'game' so that will give the best results. You could also create your own account but it is best to use the test user to see the test data that is in the database
