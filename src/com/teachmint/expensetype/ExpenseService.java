package com.teachmint.expensetype;

import java.util.List;

import com.teachmint.dao.User;
import com.teachmint.exception.IllegalSplitException;
import com.teachmint.splits.PercentSplit;
import com.teachmint.splits.Split;

public class ExpenseService {
	 public static Expense createExpense(ExpenseType expenseType, double amount, User paidBy, List<Split> splits, ExpenseData expenseData) throws IllegalSplitException {
	        switch (expenseType) {
	            case EXACT:
	                return new ExactExpense(amount, paidBy, splits, expenseData);
	            case PERCENT:
	                for (Split split : splits) {
	                    PercentSplit percentSplit = (PercentSplit) split;
	                    split.setAmount((amount*percentSplit.getPercent())/100.0);
	                }
	                return new PercentExpense(amount, paidBy, splits, expenseData);
	            case EQUAL:
	                int totalSplits = splits.size();
	                double splitAmount = ((double) Math.round(amount*100/totalSplits))/100.0;
	                for (Split split : splits) {
	                    split.setAmount(splitAmount);
	                }
	                splits.get(0).setAmount(splitAmount + (amount - splitAmount*totalSplits));
	                return new EqualExpense(amount, paidBy, splits, expenseData);
	            default:
	                return null;
	        }
	    }
	

}
