import customer.Customer;

import java.util.ArrayList;
import java.util.Scanner;

public class LuckyHotel {
    public static void main(String[] args) {
        String request = "";
        Customer customer;
        Database db = new Database();
        String name = "", password = "", email = "", confirmPass = "";

        //Create a Scanner Object
        Scanner input = new Scanner(System.in);

        do{
            //show option
            System.out.println("======== Welcome to Blacky Hotel ========");
            System.out.println("A. Register");
            System.out.println("B. Login as Admin");
            System.out.println("C. Login as Customer");
            System.out.println("D. Forgot Password");

            System.out.print("\nWhat is your request now? Please select [A-D]: ");
            request = input.nextLine();

            //register as customer
            if(request.equalsIgnoreCase("A")) {
                boolean checkPass = false;

                System.out.println("\n======== Register ========");

                System.out.print("Please enter your name :");
                name = input.nextLine();
                System.out.print("Please enter your password:");
                password = input.nextLine();

                do {
                    System.out.print("Please confirm password:");
                    confirmPass = input.nextLine();

                    if(!confirmPass.equals(password)) {
                        System.out.println("\nError : Your password not match with confirm password! " +
                                "Please enter again confirm password.\n");
                    }

                }while(!confirmPass.equals(password));

                System.out.print("Please enter your email:");
                email = input.nextLine();

                customer = new Customer(name, password, email);

                if(db.register(customer)) {
                    System.out.println("\nRegister Successfully!\n");
                }else {
                    System.out.println("\nRegister unsuccessfully! User exists!\n");
                }
            }

        }while(request.equalsIgnoreCase("A") | request.equalsIgnoreCase("B")
                | request.equalsIgnoreCase("C") | request.equalsIgnoreCase("D"));

    }
}
