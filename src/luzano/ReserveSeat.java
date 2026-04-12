package luzano;

import java.util.Scanner;
import java.util.Random;

class Node {
    int r, c;
    String name;
    Node next;

    Node(int r, int c) { this.r = r; this.c = c; }
    Node(String name) { this.name = name; }
}
class Stack {
    Node top;
    void push(int r, int c) {
        Node n = new Node(r, c);
        n.next = top;
        top = n;
    }
}

class Queue {
    Node front, rear;

    void enqueue(String name) {
        Node n = new Node(name);
        if (rear == null) {
            front = rear = n;
        } else {
            rear.next = n;
            rear = n;
        }
    }

    Node dequeue() {
        if (front == null) return null;
        Node temp = front;
        front = front.next;
        if (front == null) rear = null;
        return temp;
    }
}

public class ReserveSeat {
    static String[][] seats = new String[5][5];
    static Stack stack = new Stack();
    static Queue queue = new Queue();
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                seats[i][j] = "O";
            }
        }
        String[] sampleNames = {"Ana", "Juan", "Mark", "Liza", "Paul"};
        for (int k = 0; k < 5; k++) {
            int r = rand.nextInt(5);
            int c = rand.nextInt(5);

            if (seats[r][c].equals("O")) {
                String name = sampleNames[rand.nextInt(sampleNames.length)];
                seats[r][c] = name;
                stack.push(r, c);
            }
        }
        while (true) {
            System.out.println("\n=====Cinema=====");
            System.out.println("1.Reserve");
            System.out.println("2.Cancel");
            System.out.println("3.View");
            System.out.println("4.Exit");
            System.out.print("Choice: ");
            int ch = sc.nextInt();
            if (ch == 1) {
                sc.nextLine();
                System.out.println("\nSeats Layout:");
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        char row = (char) ('A' + i);
                        String label = row + "" + (j + 1);
                        if (seats[i][j].equals("O")) {
                            System.out.print(label + "[AVAILABLE] ");
                        } else {
                            System.out.print(label + "[TAKEN: " + seats[i][j] + "] ");
                        }
                    }
                    System.out.println();
                }
                System.out.print("\nName: ");
                String name = sc.nextLine();
                System.out.print("Choose seat (e.g. A1): ");
                String input = sc.nextLine().toUpperCase();
                int row = input.charAt(0) - 'A';
                int col = input.charAt(1) - '1';

                if (row < 0 || row >= 5 || col < 0 || col >= 5) {
                    System.out.println("Invalid seat.");
                } else if (!seats[row][col].equals("O")) {
                    System.out.println("Seat taken. Added to waiting list.");
                    queue.enqueue(name);
                } else {
                    seats[row][col] = name;
                    stack.push(row, col);
                    System.out.println(name + " reserved seat " + input);
                }
            } else if (ch == 2) {
                sc.nextLine();
                System.out.print("Enter seat to cancel (e.g. A1): ");
                String input = sc.nextLine().toUpperCase();

                int row = input.charAt(0) - 'A';
                int col = input.charAt(1) - '1';

                if (row < 0 || row >= 5 || col < 0 || col >= 5) {
                    System.out.println("Invalid seat.");
                } else if (seats[row][col].equals("O")) {
                    System.out.println("Seat already empty.");
                } else {
                    System.out.println(seats[row][col] + " removed from " + input);
                    seats[row][col] = "O";
                    Node w = queue.dequeue();
                    if (w != null) {
                        seats[row][col] = w.name;
                        System.out.println(w.name + " assigned to " + input);
                    }
                }
            } else if (ch == 3) {
                sc.nextLine();
                System.out.println("\nSeat Table:\n");

                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        char row = (char) ('A' + i);
                        String label = row + "" + (j + 1);
                        String display;
                        if (seats[i][j].equals("O")) {
                            display = label + "[FREE]";
                        } else {
                            display = label + "[" + seats[i][j] + "]";
                        }
                        System.out.printf("%-15s", display);
                    }
                    System.out.println();
                }
            } else if (ch == 4) {
                System.out.println("Exit");
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }
}
