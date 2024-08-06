package com.assignment;

import java.util.ArrayList;
import java.util.List;


public class Completed {
    private static List<String> completedList = new ArrayList<>();

    // called in queue to add item to list
    public static void addAnotherNumber(String task) {
        completedList.add(task);
    }

    // call to get completed list
    public static List<String> getCompletedList() {
        return completedList;
    }
}
