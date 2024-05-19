package com.teachmint;

import com.teachmint.splits.Split;
import java.util.HashMap;
import java.util.Map;
import com.teachmint.exception.*;
import com.teachmint.dao.User;
import com.teachmint.expensetype.Expense;
import com.teachmint.expensetype.ExpenseData;
import com.teachmint.expensetype.ExpenseService;
import com.teachmint.expensetype.ExpenseType;

import java.util.ArrayList;
import java.util.List;


public class ExpenseImpl {
	List<Expense> expenses;
    Map<String, User> userMap;
    Map<String, Map<String, Double> > record;

    public ExpenseImpl()  {
        expenses = new ArrayList<Expense>();
        userMap = new HashMap<String, User>();
        record = new HashMap<String, Map<String, Double>>();
    }

    public void addUser(User user) {
    	try {
        userMap.put(user.getId(), user);
        record.put(user.getId(), new HashMap<String, Double>());
    }
    	catch(Exception e) {
    		System.out.println("Wrong user");
    	}
    }
   
    public User getUser(Long id) {
    	try {
        if(!userMap.containsKey(id)) 
        	return userMap.get(id);
    	}
    	catch(Exception e) {
    		System.out.println("Wrong user");
    	}
		return null;
    }

    public void addExpense(ExpenseType expenseType, double amount, String paidBy, List<Split> splits, ExpenseData expenseMetadata) throws IllegalSplitException,IllegalExpenseType {
        Expense expense = ExpenseService.createExpense(expenseType, amount, userMap.get(paidBy), splits, expenseMetadata);
        expenses.add(expense);
        for (Split split : expense.getSplits()) {
            String paidTo = split.getUser().getId();
            Map<String, Double> balances = record.get(paidBy);
            if (!balances.containsKey(paidTo)) {
                balances.put(paidTo, 0.0);
            }
            balances.put(paidTo, balances.get(paidTo) + split.getAmount());

            balances = record.get(paidTo);
            if (!balances.containsKey(paidBy)) {
                balances.put(paidBy, 0.0);
            }
            balances.put(paidBy, balances.get(paidBy) - split.getAmount());
        }
    }

    public void showBalance(String userId) throws IllegalExpenseType {
        boolean isEmpty = true;
        for (Map.Entry<String, Double> userBalance : record.get(userId).entrySet()) {
            if (userBalance.getValue() != 0) {
                isEmpty = false;
                printBalance(userId, userBalance.getKey(), userBalance.getValue());
            }
        }

        if (isEmpty) {
            throw new IllegalExpenseType("null") ;
        }
    }

    public void showBalances() throws IllegalExpenseType {
        boolean isEmpty = true;
        for (Map.Entry<String, Map<String, Double>> allBalances : record.entrySet()) {
            for (Map.Entry<String, Double> userBalance : allBalances.getValue().entrySet()) {
                if (userBalance.getValue() > 0) {
                    isEmpty = false;
                    printBalance(allBalances.getKey(), userBalance.getKey(), userBalance.getValue());
                }
            }
        }

        if (isEmpty) {
        	throw new IllegalExpenseType("null") ;
        }
    }

    private void printBalance(String u1, String u2, double amount) {
        String u1Name = userMap.get(u1).getName();
        String u2Name = userMap.get(u2).getName();
        if (amount < 0) {
            System.out.println(u1Name + " owes " + u2Name + ": " + Math.abs(amount));
        } else if (amount > 0) {
            System.out.println(u2Name + " owes " + u1Name + ": " + Math.abs(amount));
        }
    }
}
		  

