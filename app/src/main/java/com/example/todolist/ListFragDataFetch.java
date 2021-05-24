package com.example.todolist;

public class ListFragDataFetch {

    String Date, Task;

    ListFragDataFetch() {}

    public ListFragDataFetch(String date, String task) {
        this.Date = date;
        this.Task = task;
    }

    public String getDate() {
        return Date;
    }

    public String getTask() {
        return Task;
    }
}
