package com.teachmint.splits;

import com.teachmint.dao.User;

public class ExactSplit extends Split {
    public ExactSplit(User user, double amount) {
        super(SplitType.EXACT, user, amount);
    }

    @Override
    public double getShare() {
        return getAmount();
    }

}
