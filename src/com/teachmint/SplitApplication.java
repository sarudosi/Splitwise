package com.teachmint;

import com.teachmint.dao.User;
import com.teachmint.expensetype.ExpenseType;
import com.teachmint.exception.*;
import com.teachmint.splits.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SplitApplication {

	public static void main(String[] args) throws IllegalSplitException, IllegalExpenseType {
	
		ExpenseImpl expenseImpl = new ExpenseImpl();

        expenseImpl.addUser(new User("u1", "User1", "pratik@teachmint.com", "1234567890"));
        expenseImpl.addUser(new User("u2", "User2", "prasad@teachmint.com", "2345678901"));
        expenseImpl.addUser(new User("u3", "User3", "swapnaj@teachmint.com", "3456789120"));
        expenseImpl.addUser(new User("u4", "User4", "rishi@teachmint.com", "9874561450"));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            String[] commands = command.split(" ");
            String commandType = commands[0];

            switch (commandType) {
                case "SHOW":
                    if (commands.length == 1) {
                        expenseImpl.showBalances();
                    } else {
                        expenseImpl.showBalance(commands[1]);
                    }
                    break;
                case "EXPENSE":
                    String paidBy = commands[1];
                    Double amount = Double.parseDouble(commands[2]);
                    int noOfUsers = Integer.parseInt(commands[3]);
                    String expenseType = commands[4 + noOfUsers];
                    List<Split> splits = new ArrayList<>();
                    switch (expenseType) {
                        case "EQUAL":
                            for (int i = 0; i < noOfUsers; i++) {
                                splits.add(new EqualSplit(expenseImpl.userMap.get(commands[4 + i]), amount));
                            }
                            expenseImpl.addExpense(ExpenseType.EQUAL, amount, paidBy, splits, null);
                            break;
                        case "EXACT":
                            for (int i = 0; i < noOfUsers; i++) {
                                splits.add(new ExactSplit(expenseImpl.userMap.get(commands[4 + i]), Double.parseDouble(commands[5 + noOfUsers + i])));
                            }
                            expenseImpl.addExpense(ExpenseType.EXACT, amount, paidBy, splits, null);
                            break;
                        case "PERCENT":
                            for (int i = 0; i < noOfUsers; i++) {
                                splits.add(new PercentSplit(expenseImpl.userMap.get(commands[4 + i]), Double.parseDouble(commands[5 + noOfUsers + i]), amount));
                            }
                            expenseImpl.addExpense(ExpenseType.PERCENT, amount, paidBy, splits, null);
                            break;
                    }
                    break;
            }
	}
	}
}
