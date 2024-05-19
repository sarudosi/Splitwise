package com.teachmint.splits;

import com.teachmint.dao.User;

public class PercentSplit extends Split {


    private double percent;

    public PercentSplit(User user, double amount, double percent) {
        super(SplitType.PERCENT, user, amount);
        this.percent = percent;
    }

    @Override
    public double getShare() {
        return getPercent();
    }

    public double getPercent() {
        return percent;
    }
}
