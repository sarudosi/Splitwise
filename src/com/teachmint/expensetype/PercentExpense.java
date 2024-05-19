package com.teachmint.expensetype;
import java.util.List;

import com.teachmint.dao.User;
import com.teachmint.exception.IllegalSplitException;
import com.teachmint.splits.*;

public class PercentExpense extends Expense {
    public PercentExpense(double amount, User paidBy, List<Split> splits, ExpenseData expenseData) throws IllegalSplitException {
        super(amount,null, paidBy, splits, expenseData);
    }

    @Override
    public void validate() throws IllegalSplitException {
        for (Split split : getSplits()) {
            if (!(split instanceof PercentSplit)) {
            	throw new IllegalSplitException("Wrong Split! Must be Percent");
            }
        }

        double totalPercent = 100;
        double sumSplitPercent = 0;
        for (Split split : getSplits()) {
            PercentSplit exactSplit = (PercentSplit) split;
            sumSplitPercent += exactSplit.getPercent();
        }

        if (totalPercent != sumSplitPercent) {
        	 throw new IllegalSplitException("Incorrect Percentage Split" + totalPercent);
        }
            
    }

}
