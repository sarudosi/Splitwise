package com.teachmint.expensetype;

import java.util.List;

import com.teachmint.dao.User;
import com.teachmint.exception.IllegalSplitException;
import com.teachmint.expensetype.ExpenseData;
import com.teachmint.splits.Split;

public abstract class Expense {
	
    private double amount;
    final private ExpenseType expenseType;
    private User paidBy;
    private List<Split> splits;
    private ExpenseData metadata;
	public Expense(double amount, ExpenseType expenseType, User paidBy, List<Split> splits, ExpenseData metadata) {
		super();
		this.amount = amount;
		this.expenseType = expenseType;
		this.paidBy = paidBy;
		this.splits = splits;
		this.metadata = metadata;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public User getPaidBy() {
		return paidBy;
	}
	public void setPaidBy(User paidBy) {
		this.paidBy = paidBy;
	}
	public List<Split> getSplits() {
		return splits;
	}
	public void setSplits(List<Split> splits) {
		this.splits = splits;
	}
	public ExpenseData getMetadata() {
		return metadata;
	}
	public void setMetadata(ExpenseData metadata) {
		this.metadata = metadata;
	}
	public ExpenseType getExpenseType() {
		return expenseType;
	}
	
	public abstract void validate() throws IllegalSplitException;
	
	@Override
	public String toString() {
		return "Expense [amount=" + amount + ", expenseType=" + expenseType + ", paidBy=" + paidBy + ", splits="
				+ splits + ", metadata=" + metadata + "]";
	}

    
}

