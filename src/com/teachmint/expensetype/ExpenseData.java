package com.teachmint.expensetype;

import com.teachmint.exception.IllegalSplitException;

public class ExpenseData {
	
	private String name;
    private String imgUrl;
    private String notes;

    public ExpenseData(String name, String imgUrl, String notes) throws IllegalSplitException {
        this.name = name;
        this.imgUrl = imgUrl;
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
