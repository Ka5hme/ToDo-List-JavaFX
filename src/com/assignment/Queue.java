package com.assignment;

import java.util.*;

public class Queue {

    private String[] q; // Holds queue elements
    private int front;  // Next item to be removed
    private int rear;   // Next slot to fill
    private int size;   // Number of items in queue

    public Queue(int capacity) {
        q = new String[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }

    public int capacity() {
        return q.length; // Return the capacity of the queue
    }

    public void enqueue(String s) {
        if (size == q.length)
            return; // Do nothing if the queue is full
        else {
            size++;
            q[rear] = s;
            rear++;
            if (rear == q.length) rear = 0; // Wrap around if needed
        }
    }

    public String peek() {
        if (empty())
            return ("Add A Task"); // Return message if the queue is empty
        else
            return q[front]; // Return the front element
    }

    public String dequeue() {
        if (empty())
            return null; // Return null if the queue is empty
        else {
            size--;
            String value = q[front];
            q[front] = ("Completed"); // Facilitate garbage collection
            front++;
            if (front == q.length) front = 0; // Wrap around if needed
            Completed.addAnotherNumber(value); // Add dequeue element to completed List
            return value;
        }
    }

    public boolean empty() {
        return size == 0; // Check if the queue is empty
    }

    public String toString() {
        StringBuilder sBuilder = new StringBuilder();
        //sBuilder.append("front = " + front + "; ");       dont need to display this information
        //sBuilder.append("rear = " + rear + "\n");
        for (int k = 0; k < q.length; k++) {
            if (q[k] != null)
                sBuilder.append(k+1 + ": " + q[k]);     // K+1 looks better to user
            else
                sBuilder.append(k+1 + ":");
            if (k != q.length - 1)
                sBuilder.append("\n");
        }
        return sBuilder.toString(); // Return a string representation of the queue
    }
}
