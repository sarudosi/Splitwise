package com.teachmint.expensetype;

import java.util.List;

import com.teachmint.dao.User;
import com.teachmint.exception.IllegalSplitException;
import com.teachmint.splits.*;

public class EqualExpense extends Expense {
    public EqualExpense(double amount, User paidBy, List<Split> splits, ExpenseData expenseData)  throws IllegalSplitException{
        super(amount, null,paidBy, splits, expenseData);
    }

    @Override
    public void validate() throws IllegalSplitException {
        for (Split split : getSplits()) {
            try {
				if (!(split instanceof EqualSplit)) {
					throw new IllegalSplitException("Wrong Split! Must be Equal");
				}
			} catch (IllegalSplitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         
    }
    }
	

}
