// Name: David Lincoln
// Class: CS 3305/Section Online
// Term: Fall 2024
// Instructor: Umama Tasnim
// Assignment: 2

import java.util.*;
import java.io.*;
import java.util.LinkedList;

class LinkedListsDavidLincoln {

    //Employee class - field elements for the employee profile
    public static class Employee {
        public int employeeID;
        public String employeeName;
        public String employeePosition;
        public Double employeeSalary;
    }

    // Node class - stores employee information and refer to the next node.
    private static class Node {
        Employee employee; //holds employee object and represents data stored in the node.
        Node next; //Link - references next node

        //Constructor for the node class
        Node(Employee employee) {
            this.employee = employee;
            this.next = null;
        }
    }

    //Employee management List
    public static class LinkedList {

        //First node
        private Node head;

        //Employee management methods
        public void addEmployee(Employee emp) {
            //objects
            Node newNode = new Node(emp);
            //checks if the head is empty
            if(head == null) {
                head = newNode;
            }else { //adds a new node to the list
                Node current = head;
                while(current != null) {
                    //Checks for duplicate node entries by ID
                    if(current.employee.employeeID == emp.employeeID) {
                        System.out.println("Duplicate employee ID. Re-enter a different ID.");
                        return;
                    }
                    //
                    if (current.next == null) {
                        break;
                    }
                    current = current.next;
                }
                //Adding a new node
                current.next = newNode;
            }
            System.out.println();
            System.out.println("Employee added successfully.");

        }

        //Remove an employee by ID
        public void removeEmployee(int employeeID) {
            //At the end of the list, the head would return null and signal the employee id not found
            if (head == null) {
                System.out.println();
                System.out.println("No employees Found.");
                return;
            }

            //if the employeeID is identified then the node would be skipped and removed
            if (head.employee.employeeID == employeeID) {
                head = head.next;
                System.out.println();
                System.out.println("Employee removed successfully.");
                return;
            }

            Node current = head;
            Node previous = null;
            //finding the node to remove by traversing the list
            while (current != null && current.employee.employeeID != employeeID) {
                previous = current;
                current = current.next;
            }
            //if the node is found
            if (current != null) {
                //Skipping the node removes it from the list
                previous.next = current.next;
                System.out.println("Employee removed successfully.");
            } else {
                System.out.println();
                System.out.println("Employee with ID: " + employeeID + " not found.");
            }
        }

        public void getEmployee(int employeeID){
            Node current = head;
            while(current != null) {
                if(current.employee.employeeID == employeeID) {
                    System.out.println();
                    System.out.println("Employee ID: " + current.employee.employeeID);
                    System.out.println("Employee Name: " + current.employee.employeeName);
                    System.out.println("Employee Position: " + current.employee.employeePosition);
                    System.out.println("Employee Salary: " + current.employee.employeeSalary);
                    System.out.println();
                    return;
                }
                current = current.next;
            }
            System.out.println();
            System.out.println("Employee ID: " + employeeID + " not found.");
        }

        public void listAll() {
            Node current = head;

            if(current == null) {
                System.out.println("No employee found.");
                System.out.println();
                return;
            }

            while(current != null) {
                System.out.println();
                System.out.println("Employee ID: " + current.employee.employeeID);
                System.out.println("Employee Name: " + current.employee.employeeName);
                System.out.println("Employee Position: " + current.employee.employeePosition);
                System.out.println("Employee Salary: " + current.employee.employeeSalary);
                System.out.println();
                current = current.next;
            }
        }
    }

    //Menu program
    public static void main (String[] args) {
        //Objects
        Scanner scan = new Scanner(System.in);
        LinkedList list = new LinkedList();

        //title
        System.out.println("EMPLOYEE MANAGEMENT SYSTEM");

        int selection;
        do {
            //selection menu
            System.out.println("-----------------------------------------");
            System.out.println("[1] New Employee");
            System.out.println("[2] Remove Employee");
            System.out.println("[3] Get Employee Information");
            System.out.println("[4] Exit");
            selection = scan.nextInt();

            //menu
            switch (selection) {
                case 1:
                    //creates a new employee object evertime a new employee is added
                    Employee employee = new Employee();

                    System.out.println("Enter employee id: ");
                    employee.employeeID = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter employee name: ");
                    employee.employeeName = scan.nextLine();
                    System.out.println("Enter employee position: ");
                    employee.employeePosition = scan.nextLine();
                    System.out.println("Enter employee salary: ");
                    employee.employeeSalary = scan.nextDouble();

                    //Display - employee id, employee name, employee position, employee salary
                    System.out.println();
                    System.out.println("You entered: " + employee.employeeID + " " + employee.employeeName + " " + employee.employeePosition + " " + employee.employeeSalary);
                    System.out.println("Is this correct (Y/N): ");
                    String confirmation = scan.next();
                    scan.nextLine();

                    if (confirmation.equalsIgnoreCase("Y")) {
                        //Logic for storing employee information
                        list.addEmployee(employee);

                        //Confirmation message added to method
                        System.out.println();
                        //return to main menu

                    } else if (confirmation.equalsIgnoreCase("N")) {
                        //Re-enter details
                        System.out.println("Re-enter employee details.");
                        System.out.println();

                    } else {
                        System.out.println();
                        System.out.println("Returning to main menu");
                        System.out.println();
                    }
                    break;

                case 2:
                    System.out.println("Enter employee ID: ");
                    int removeEmp = scan.nextInt();
                    list.removeEmployee(removeEmp);
                    break;

                case 3:
                    int search = 0;
                    do {
                        System.out.println("[1] Search by ID");
                        System.out.println("[2] List All");
                        System.out.println("[3] Return");
                        search = scan.nextInt();

                        switch (search) {
                            case 1:
                                System.out.println("Enter Employee ID: ");
                                int searchID = scan.nextInt();
                                list.getEmployee(searchID);
                                break;

                            case 2:
                                list.listAll();
                                System.out.println();
                                break;
                            case 3:
                                System.out.println();
                                System.out.println("Returning to main menu...");
                                System.out.println();
                                break;

                            default:
                                System.out.println("Invalid Selection.");
                                break;
                        }
                    } while (search != 3);
                    break;

                case 4:
                    System.out.println("Exiting program....Good bye :)");
                    System.out.println();
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
                    System.out.println();
                    break;
            }
        } while (selection != 4);
        scan.close();
    }
}