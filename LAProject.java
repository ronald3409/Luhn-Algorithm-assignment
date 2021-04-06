// Throughout this project, the use of data structures are not permitted such as methods like .split and .toCharArray
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
// More packages may be imported in the space below

class LAProject{
    public static void main(String[] args) throws IOException {
        // Please do not edit any of these variables
        Scanner reader = new Scanner(System.in);
        String userInput, enterCustomerOption, generateCustomerOption, exitCondition;
        enterCustomerOption = "1";
        generateCustomerOption = "2";
        exitCondition = "9";

        // More variables for the main may be declared in the space below


        do{
            printMenu();                                    // Printing out the main menu
            userInput = reader.nextLine();                  // User selection from the menu

            if (userInput.equals(enterCustomerOption)){
                String information = enterCustomerInfo();
            }
            else if (userInput.equals(generateCustomerOption)) {
                // Only the line below may be editted based on the parameter list and how you design the method return
                generateCustomerDataFile();
            }
            else{
                System.out.println("Please type in a valid option (A number from 1-9)");
            }

        } while (!userInput.equals(exitCondition));         // Exits once the user types 
        
        reader.close();
        System.out.println("Program Terminated");
    }
    public static void printMenu(){
        System.out.println("Customer and Sales System\n"
        .concat("1. Enter Customer Information\n")
        .concat("2. Generate Customer data file\n")
        .concat("3. Report on total Sales (Not done in this part)\n")
        .concat("4. Check for fraud in sales data (Not done in this part)\n")
        .concat("9. Quit\n")
        .concat("Enter menu option (1-9)\n")
        );
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static String enterCustomerInfo() throws IOException {
        Scanner reader = new Scanner(System.in);
        System.out.println();
        System.out.print("First name: ");
        String firstname = reader.nextLine();
        //user eneters first name
        System.out.print("Last name: ");
        String lastname = reader.nextLine();
        //user enters last name
        System.out.print("City: ");
        String city = reader.nextLine();
        //user enters City
        System.out.print("Postal Code: ");
        String postalcode = reader.nextLine();
        //user enters postal code
        int pcnum = postalcode.length();
        //sees how many characters the postal code entered has
        while (pcnum < 3){
        //if number of characters is less than three
            System.out.println("Postal Code is invalid");
            System.out.print("Postal Code: ");
        postalcode = reader.nextLine();
        pcnum = postalcode.length();
        }
        boolean validity = validatePostalCode(postalcode);
        //checks if postal code is valid
        while (validity == false){
        //if postal is invalid
            System.out.println("Postal Code is invalid");
            System.out.print("Postal Code: ");
        postalcode = reader.nextLine();
        validity = validatePostalCode(postalcode);
        }
        System.out.print("Credit Card number: ");
        String creditcard = reader.nextLine();
        //user enters credit card
        int ccnum = creditcard.length();
        //sees how many charcaters the credit card number entered has
        while (ccnum < 9){
        //if number of characters is less than 9
            System.out.println("Credit Card number is invalid");
            System.out.print("Credit Card number: ");
        creditcard = reader.nextLine();
        ccnum = creditcard.length();
        }
        reader.close();
        String info = firstname + ", " + lastname + ", " + city + ", " + postalcode + ", " + creditcard;
        //combines all info
        return info;
        //returns info
    }
    public static boolean validatePostalCode(String Pcode) throws IOException {
        String filename = "postal_codes.csv";
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        int num = -1;
        //use to see if postal code is valid
        boolean validation;
        int PCchar = Pcode.length();
        //checks to see how many characters are in postal code entered
        if (PCchar < 3){
            //if less than three
                validation = false;
        }
        else if (PCchar >= 3){
            String PC3 = Pcode.substring(0,3);
            //use to get first three characters
                while(line != null){
                //goes through all lines to see if user code is in the file
                    line = reader.readLine();
                    if (line != null){
                    //checks to see if first 3 characters of postal code match postal codes in file
                        String linechar3 = line.substring(0,3);
                        //gets postal code of each line
                        if (PC3.equals(linechar3)){
                        //if the postal code if valid
                            num = 1;
                            break;
                        }
                    }
                }
        }
        if (num < 0){
        //if postal code is not in file
            validation = false;
        }
        else {
        //if postal code is found in file
            validation = true;
        }
        reader.close();
        return validation;
        //returns true or false
    }
    public static void validateCreditCard(){
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static void generateCustomerDataFile(){
    }
    /*******************************************************************
    *       ADDITIONAL METHODS MAY BE ADDED BELOW IF NECESSARY         *
    ********************************************************************/
}
