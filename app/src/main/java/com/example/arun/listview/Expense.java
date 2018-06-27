package com.example.arun.listview;

public class Expense {

    private String expense;
    private int cost ;

    public Expense(String expense, int cost) {
        this.expense = expense;
        this.cost = cost;
    }

    public String getExpense() {
        return expense;
    }

    public int getCost() {
        return cost;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
