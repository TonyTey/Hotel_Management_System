import Database.Database;
import admin.Admin;
import admin.AdminManager;
import customer.Customer;
import customer.CustomerManager;

import java.util.Scanner;

public class LuckyHotel {
    public static void main(String[] args) {
        String request = "";
        Customer customer;
        Admin admin;
        Database db = new Database();
        CustomerManager customerManager = new CustomerManager();
        AdminManager adminManager = new AdminManager();
        String name = "", password = "", email = "", confirmPass = "";

        //Create a Scanner Object
        Scanner input = new Scanner(System.in);

        //if input request out of a, b, c ,d, program will ask user input request again
        do {
            //show option
            System.out.println("======== Welcome to Blacky Hotel ========");
            System.out.println("A. Register");
            System.out.println("B. Login as Admin");
            System.out.println("C. Login as Customer");
            System.out.println("D. Forgot Password");

            System.out.print("\nWhat is your request now? Please select [A-D], Q for quit program: ");
            request = input.nextLine();

            //register as customer
            if (request.equals("A")) {
                do {
                    boolean checkPass = false;
                    String choice = "";

                    System.out.println("\n======== Register ========");

                    System.out.print("Please enter your name :");
                    name = input.nextLine();
                    System.out.print("Please enter your password:");
                    password = input.nextLine();

                    do {
                        System.out.print("Please confirm password:");
                        confirmPass = input.nextLine();

                        if (!confirmPass.equals(password)) {
                            System.out.println("\nError : Your password not match with confirm password! " +
                                    "Please enter again confirm password.\n");
                        }

                    } while (!confirmPass.equals(password));

                    System.out.print("Please enter your email:");
                    email = input.nextLine();

                    customer = new Customer(name, password, email);

                    //register customer detail into database, if user are fresh customer.
                    if (customerManager.register(customer)) {
                        System.out.println("\nRegister Successfully!\n");
                    } else {
                        System.out.println("\nRegister unsuccessfully! User exists! Please try another email.");

                        //user make decision continue register or not while failed to register as customer
                        do {
                            System.out.print("\nContinue to register? [Y/N] :");
                            choice = input.nextLine();

                            if (choice.equals("N"))
                                break;
                            else if(!choice.equals("Y"))
                                System.out.println("Error :Wrong input! Please input again.");
                            else
                                break;

                        } while (!choice.equals("N") || !choice.equals("Y"));
                    }

                    if(choice.equals("N")) {
                        System.out.println();
                        break;
                    }
                }while(!customerManager.register(customer));

                //Login as Admin
            }else if(request.equals("B")) {
                do{
                    String choice = "";
                    System.out.println("\n======== Admin Login Phase ========");

                    System.out.print("Please enter your email :");
                    email = input.nextLine();
                    System.out.print("Please enter your password:");
                    password = input.nextLine();

                    admin = new Admin(email, password);

                    //check the admin log in account
                    if(adminManager.selectAdmin(admin)) {
                        System.out.println("\nLogin Successfully!\n");
                    } else {
                        System.out.println("\nLogin unsuccessfully! Wrong password or email!");

                        //user make decision continue login or not while failed to log in admin
                        do {
                            System.out.print("\nContinue to login? [Y/N] :");
                            choice = input.nextLine();

                            if (choice.equals("N"))
                                break;
                            else if(!choice.equals("Y"))
                                System.out.println("Error :Wrong input! Please input again.");
                            else
                                break;

                        } while (!choice.equals("N") || !choice.equals("Y"));
                    }

                    if(choice.equals("N")) {
                        System.out.println();
                        break;
                    }

                }while(!adminManager.selectAdmin(admin));

            }else if(request.equals("C")) {
                do{
                    String choice = "";
                    System.out.println("\n======== Customer Login Phase ========");

                    System.out.print("Please enter your email :");
                    email = input.nextLine();
                    System.out.print("Please enter your password:");
                    password = input.nextLine();

                    customer = new Customer(email, password);

                    //check the customer log in account
                    if(customerManager.selectCustomer(customer)) {
                        System.out.println("\nLogin Successfully!\n");
                    } else {
                        System.out.println("\nLogin unsuccessfully! Wrong password or email!");

                        //user make decision continue login or not while failed to log in customer
                        do {
                            System.out.print("\nContinue to login? [Y/N] :");
                            choice = input.nextLine();

                            if (choice.equals("N"))
                                break;
                            else if(!choice.equals("Y"))
                                System.out.println("Error :Wrong input! Please input again.");
                            else
                                break;
                        } while (!choice.equals("N") || !choice.equals("Y"));
                    }

                    if(choice.equals("N")) {
                        System.out.println();
                        break;
                    }

                }while(!customerManager.selectCustomer(customer));

            }else if(request.equals("D")) {
                String[] user;
                String choice = "";

                do {
                    System.out.println("\n======== Recover Password Phase ========");
                    System.out.print("Please enter your email:");
                    email = input.nextLine();

                    user = db.checkUser(email);

                    if (user[0] != null) {
                        System.out.println("\nUser found!");
                        System.out.println("\n ======== User ========");
                        System.out.println("Type: " + user[3]);
                        System.out.println("Name: " + user[0]);
                        System.out.println("Email: " + user[1]);
                        System.out.println("Password: " + user[2]);
                    } else {
                        System.out.println("\nUser not found!");
                        System.out.println("\nError : Please make sure enter the correct email!");
                    }

                    //user make decision continue recover password or not while failed to verify email
                    do {
                        System.out.print("\nContinue to recover password? [Y/N] :");
                        choice = input.nextLine();

                        if (choice.equals("N"))
                            break;
                        else if (!choice.equals("Y"))
                            System.out.println("Error :Wrong input! Please input again.");
                        else
                            break;
                    } while (!choice.equals("N") || !choice.equals("Y"));

                    if (choice.equals("N")) {
                        System.out.println();
                        break;
                    }else {
                        user[0] = null;
                    }

                } while (user[0] == null);
            }else {
                //Q for quit program
                if (request.equalsIgnoreCase("Q")) {
                    System.out.println("\nExit System successfully!");
                    break;

                    //prompt unknown request error to user
                }else if(!request.equalsIgnoreCase("A") | !request.equalsIgnoreCase("B")
                        | !request.equalsIgnoreCase("C") | !request.equalsIgnoreCase("D")) {
                    System.out.println("\nError :Unknown request. Please enter again!\n");
                }
            }

        } while (!request.equalsIgnoreCase("A") | !request.equalsIgnoreCase("B")
                | !request.equalsIgnoreCase("C") | !request.equalsIgnoreCase("D"));
    }

}
