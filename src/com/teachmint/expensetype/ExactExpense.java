package com.teachmint.expensetype;
import java.util.List;

import com.teachmint.dao.User;
import com.teachmint.exception.IllegalSplitException;
import com.teachmint.splits.*;


public class ExactExpense extends Expense {
    public ExactExpense(double amount, User paidBy, List<Split> splits, ExpenseData expenseMetadata) throws IllegalSplitException {
        super(amount, null,paidBy, splits, expenseMetadata);
    }

    @Override
    public void validate() throws IllegalSplitException {
        for (Split split : getSplits()) {
            if (!(split instanceof ExactSplit)) {
                throw new IllegalSplitException("Wrong Split! Must be Exact");
            }
        }

        double totalAmount = getAmount();
        double sumSplitAmount = 0;
        for (Split split : getSplits()) {
            ExactSplit exactSplit = (ExactSplit) split;
            sumSplitAmount += exactSplit.getAmount();
        }

        if (totalAmount != sumSplitAmount) {
        	throw new IllegalSplitException("Wrong Split! Must be Exact");
        }
            
    }

}
