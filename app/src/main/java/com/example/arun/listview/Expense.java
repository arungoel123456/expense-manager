package com.example.arun.listview;

public class Expense {


    private int id;

    private String expense;
    private int cost ;
    private String date;
    private String time;

    public Expense(String expense, int cost , String date, String time) {
        this.expense = expense;
        this.cost = cost;
        this.date= date;
        this.time=time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
