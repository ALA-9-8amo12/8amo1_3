package com.example.amazigh;

public class Category {
    private String category;
    private int id, Limit;

//    public Category(String category, int id, int Limit)
//    {
//        this.category = category;
//        this.id = id;
//        this.Limit = Limit;
//    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLimit() {
        return Limit;
    }

    public void setLimit(int limit) {
        Limit = limit;
    }
}
