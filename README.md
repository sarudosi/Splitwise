# Splitwise
Your objective is to create the backend for an expense sharing application. An expense sharing application is where you can add your expenses and split it among different people. The app keeps balances between people as in who owes how much to whom.

**Functional Requirements**
User: Each user should have a userId, name, email, mobile number.

Expense: Could either be EQUAL, EXACT or PERCENT

Users can add any amount, select any type of expense and split with any of the available users.

The percent and amount provided could have decimals upto two decimal places.

In case of percent, you need to verify if the total sum of percentage shares is 100 or not.

In case of exact, you need to verify if the total sum of shares is equal to the total amount or not.

The application should have a capability to show expenses for a single user as well as balances for everyone.

When asked to show balances, the application should show balances of a user with all the users where there is a non-zero balance.

The amount should be rounded off to two decimal places. Say if User1 paid 100 and amount is split equally among 3 people. Assign 33.34 to first person and 33.33 to others.

**Input**

You can create a few users in your main method. No need to take it as input.

There will be 3 types of input:

Expense in the format: EXPENSE <user-id-of-person-who-paid> <no-of-users> <space-separated-list-of-users> <EQUAL/EXACT/PERCENT> <space-separated-values-in-case-of-non-equal>

Show balances for all: SHOW

Show balances for a single user: SHOW <user-id>
