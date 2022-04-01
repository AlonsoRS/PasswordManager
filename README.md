# Password manager 

## Introduction
This project aims to create a password management software that allows the user to record essential information such as **password**, **username** 
and the **website** for which these credentials allow access to. The software should be friendly enough for any person to make use of it.
Managing passwords is a tedious task, specially nowadays since websites ask for many specifications for a password to be valid, making them
harder to remember.

The software is pretty simple. A user can create "Collections" that may hold an unlimited number of "Users". 
For example, a user may want to create a Collection named "Entertainment" that stores "Users" with the information necessary to access
entertainment services such as Netflix or Facebook.


## Current User stories:
P1:
- As a user, I want to be able to create a Collection
- As a user, I want to be able to add a User to a Collection
- As a user, I want to be able to create a User with a name, password and website.
- As a user, I want to be able to see the username, password and website of all Users in all Collections
- As a user, I want to be able to search for all the Users that have a specific username

P2:
- As a user, I want to be able to save my Collections to file
- As a user, I want to be able to load my Collections from file

P3:
- Implemented a graphic user interface for all user stories



## Phase 4: Task 2
Wed Mar 30 17:58:19 PDT 2022
Collection "Leisure" was created

Wed Mar 30 17:58:23 PDT 2022
Collection "Banking" was created

Wed Mar 30 17:58:30 PDT 2022
Added User "Maria" to the "Banking" Collection

Wed Mar 30 17:58:34 PDT 2022
Added User "Jhon" to the "Banking" Collection

Wed Mar 30 17:58:50 PDT 2022
Added User "Emilly" to the "Leisure" Collection

Wed Mar 30 17:58:57 PDT 2022
User "Lucas24" was not found in any Collection

Wed Mar 30 17:59:01 PDT 2022
User "Emilly" was found

## Phase 4: Task 3
There are many refactorings options that could improve the design of this project: 
- The Password Class could be refactored to be a field of type String of the User Class because the only methods of Password are to return or modify its 
only field which is a String. 

- Additional Classes could be implemented to increase cohesion of the GraphicUserInterface Class. For instance, a Class 
called Button and a Class called MenuOption could be used to manage when a button is pressed and when an option is chosen 
respectively. This would allow for multiple buttons to be named the same because, as of now, the only way of differentiating 
buttons or options is through their name (i.e. Search User). This would be useful if we want a button or option to perform
the same operation but with a different implementation

- Furthermore, a bidirectional relationship could be added between Collection and User (add a field of type Collection in User). 
This would make it easier to identify what Collection a specific User belongs to. 



## Image Citations:
- SaveIcon: https://icons8.com/icon/13279/save
- LoadIcon: https://icons8.com/icon/RLPVoj6CHEpb/load
- Background Image: https://vpnoverview.com/password-managers/