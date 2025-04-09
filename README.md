# cse237-project25

Team Members:

* Bhakti Malhotra
* Martin Ibarra
* Tyler Dinh
* Jacob Mathew

For each iteration you should answer the following:

What user stories were completed this iteration?  

Iteration 1:  

[A user should be able to register a new account with a username and password](https://github.com/CSE237SP25/project-socialnetworkrejects/issues/7)  
[A user should be able to login using a username and password](https://github.com/CSE237SP25/project-socialnetworkrejects/issues/6)  
[A user should be able to transfer money to another user in testing (only backend implementation)](https://github.com/CSE237SP25/project-socialnetworkrejects/issues/3)  
[A user should be able to deposit money into their account](https://github.com/CSE237SP25/project-socialnetworkrejects/issues/5)  
[A user should be able to withdraw money from their account](https://github.com/CSE237SP25/project-socialnetworkrejects/issues/4)  
[A user should not be able to enter negative amounts of money for withdraw, deposit, or transfer](https://github.com/CSE237SP25/project-socialnetworkrejects/issues/27)  
[An admin user should be able to login to their account using a username and password](https://github.com/CSE237SP25/project-socialnetworkrejects/issues/8)  
[A user should be able to access the banking app through a text GUI](https://github.com/CSE237SP25/project-socialnetworkrejects/issues/26)  
[A user should not be able to enter in any options EXCEPT the options listed specifically in the menu, ignoring case](https://github.com/CSE237SP25/project-socialnetworkrejects/issues/28)

Iteration 2:

[A user should not be able to make fraudulent transactions and should be notified if they have potentially made one](https://github.com/CSE237SP25/project-socialnetworkrejects/issues/12)  
[A user should be able to calculate the amount of interest on the funds within their savings account](https://github.com/CSE237SP25/project-socialnetworkrejects/issues/14)  
[A user should be able to view their own transaction history](https://github.com/CSE237SP25/project-socialnetworkrejects/issues/16)  
[The admin user should be able to view a transaction history of ALL transactions made by Users](https://github.com/CSE237SP25/project-socialnetworkrejects/issues/13)  
[A user should be able to open a savings account (the one that is opened on registration)](https://github.com/CSE237SP25/project-socialnetworkrejects/issues/1)  
[A user should be able to open a checkings account](https://github.com/CSE237SP25/project-socialnetworkrejects/issues/2)  
[A user should be able to transfer money to another (arbitrary) user (mainly the frontend implementation)](https://github.com/CSE237SP25/project-socialnetworkrejects/issues/30)  
[All users' transactions should be assigned a unique ID (UUID) and added to their transaction history](https://github.com/CSE237SP25/project-socialnetworkrejects/issues/15)  

Not specifically tailored to a user story, but 4 additional issues (#32, #33, #34, #35) were made and subsequently assigned to handle project feedback.  




What user stories do you intend to complete next iteration?
  
Iteration 1:  

To organize the Kanban Board for the project, we created an additional "Backlog" column to hold potential user stories for future iterations/sprints of the project. In this column, some stories include [A user should be able to transfer money to another user (mainly frontend implementation)](https://github.com/CSE237SP25/project-socialnetworkrejects/issues/30) as described below, transaction-related user stories such as [A user should be able to view their transaction history](https://github.com/CSE237SP25/project-socialnetworkrejects/issues/16), [Each transaction should be assigned a unique ID (UUID) and added to a backlog](https://github.com/CSE237SP25/project-socialnetworkrejects/issues/15), [The admin user should be able to view a backlog of ALL transactions made by Users](https://github.com/CSE237SP25/project-socialnetworkrejects/issues/13), and [The system should be able to detect a *potential* fraudulent transaction](https://github.com/CSE237SP25/project-socialnetworkrejects/issues/12). In terms of the capabilities of the user, we want to extend the use of their balance for [A user should be able to open a checking account](https://github.com/CSE237SP25/project-socialnetworkrejects/issues/2) and [A user should be able to open a savings account](https://github.com/CSE237SP25/project-socialnetworkrejects/issues/1). We also want to have the functionality for [A user should be able to calculate the amount of interest made on their funds within their savings account](https://github.com/CSE237SP25/project-socialnetworkrejects/issues/14).

Iteration 2:
We intend to complete user stories related to updating the UI and implementing all of the user actions fully. Because Iteration 3 is the last iteration, it is important that we ensure that if our program were to be adapted by a real bank, that we do not leak sensitive information about other users or the bank itself. It is important for our group to update the admin's abilities to watch over a user flagged as potentially making unallowed transactions, and implementing that restriction on the user itself. Updating the UI could mean we streamline the process for the user to navigate through their different actions that they can take. These are some of the many possibilities our group has considered.

Is there anything that you implemented but doesn't currently work?
Iteration 1:  

Although we currently have the transfer method working in the backend and verified with testing, we want to implement it with our text GUI frontend so that users can transfer money to each other. To accomplish this, we will identify the same steps that were taken to include the deposit and withdraw methods in the text GUI frontend and apply them to the transfer method. We must also identify which direction we want to go with implementing the user's selection of "transfer" within the menu, because we could either have the user input a username of a user that we want to transfer to, or create an ID variable for the BankAccount class and have the user input the ID of the account they want to transfer to.

Iteration 2:

Although the calculate interest method does work with any specified number of years, and the user can select it as an action, it only returns the interest for the savings account over 1 year, because there is no input handling that was made during this iteration for the user to put the number of years they would like to see for interest. This would be a simple fix, by using the same Scanner object we have been using. Additionally, the transfer method would need more functionality for a user to transfer to another user in the system rather than the current *arbitrary* user that the program handles. This would require us to search for the other input user in the system, and then do actions based on if they exist or not.

What commands are needed to compile and run your code from the command line (please provide a script that users can run to launch your program)?  

To run the program, run the command
```
chmod +x RunBankingApp.sh
./RunBankingApp.sh
```
in the root folder of the project.
