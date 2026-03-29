package luzano;

import java.util.Scanner;

class Node {
    int row;
    int col;
    Node next;

    Node(int r, int c) {
        row = r;
        col = c;
        next = null;
    }
}


class Stack {
    Node top;

    void push(int r, int c) {
        Node newNode = new Node(r, c);
        newNode.next = top;
        top = newNode;
    }

    Node pop() {
        if (top == null) return null;

        Node temp = top;
        top = top.next;
        return temp;
    }

    boolean isEmpty() {
        return top == null;
    }
}


class Queue {
    Node front, rear;

    void enqueue(int r, int c) {
        Node newNode = new Node(r, c);

        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    Node dequeue() {
        if (front == null) return null;

        Node temp = front;
        front = front.next;

        if (front == null) rear = null;

        return temp;
    }

    boolean isEmpty() {
        return front == null;
    }
}

public class ReserveSeat {

    static String[][] seats = new String[3][3];
    static Stack stack = new Stack();
    static Queue queue = new Queue();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        initializeSeats();

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. View Seats");
            System.out.println("2. Reserve Seat");
            System.out.println("3. Undo Last Reservation");
            System.out.println("4. Add to Waiting List");
            System.out.println("5. Exit");
            System.out.print("Choice: ");

            int choice;


            
