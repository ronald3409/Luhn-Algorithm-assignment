// Throughout this project, the use of data structures are not permitted such as methods like .split and .toCharArray
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
		StringBuffer information = new StringBuffer();
		
        do{
            printMenu();                                    // Printing out the main menu
            userInput = reader.nextLine();                  // User selection from the menu

            if (userInput.equals(enterCustomerOption)){
            	String custInfo = enterCustomerInfo(reader);
				//stores information from user 
            	long id = generateId();
                information = information.append(id).append(",").append(custInfo).append("\n");
            }
            else if (userInput.equals(generateCustomerOption)) {
                // Only the line below may be editted based on the parameter list and how you design the method return
        	    String path = null;

        	    if (information.length() > 0) {
        		while (path == null || path.isBlank()) {
        			System.out.println("Please enter a path for your file (the path cannot be C:\\): ");
					//prompts user to enter path for file
        			path = reader.next();
					//allows user to input path
        		}
        		
        		String fileName = null;
        		while (fileName == null || fileName.isBlank()) {
        			System.out.println("Please enter a file name (must end in .csv): ");
					//prompts user for name of file
        			fileName = reader.next();
					//allows user to input name
        		}
        		
        		//calls to generate data file
                generateCustomerDataFile(path, fileName, information);
                path = null;
                fileName = null;
                information = new StringBuffer();
        	    } else {
        	    	System.out.println("There is no data to add to the file");
        	    }
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
    public static String enterCustomerInfo(Scanner reader) throws IOException {
    //    Scanner reader = new Scanner(System.in);
        System.out.println();
        System.out.print("First name: ");
		//prompts user for first name
        String firstname = reader.nextLine();
        //user eneters first name
        System.out.print("Last name: ");
		//prompts user for last name
        String lastname = reader.nextLine();
        //user enters last name
        System.out.print("City: ");
		//prompts user for city
        String city = reader.nextLine();
        //user enters City
        System.out.print("Postal Code: ");
		//prompts user for postal code
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
		//prompts user for Credit Card number
        String creditcard = reader.nextLine();
		//user enters credit card number
        creditcard = creditcard.trim();
        // checks input credit card is valid card
        boolean isValidCreditCard = validateCreditCard(creditcard);
        while (!isValidCreditCard){
			//if user enters in valid credit card number
        	System.out.print("Invalid credit card number. Please enter again: ");
        	creditcard = reader.nextLine();
        	isValidCreditCard = validateCreditCard(creditcard);
        }
        String info = firstname + ", " + lastname + ", " + city + ", " + postalcode + ", " + creditcard;
        //combines all info
        return info;
        //returns info
    }
    public static boolean validatePostalCode(String Pcode) throws IOException {
        String filename = "postal_codes.csv"; 
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
		//skips first line
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

    
    public static boolean validateCreditCard(String creditCard) {
    	// checks if inputted credit card number is null, empty or smaller than 9 numbers, then returns false
		if (creditCard == null || creditCard.isBlank() || creditCard.length() < 9 ) {
			return false;
		}
		// reverses credit card order
		String reversedCreditCard = reverseOrder(creditCard);
		// gets the sum of odd digits from reversed credit card number
		int oddSum = calculateSumFromOddDigits(reversedCreditCard);
		// gets the sum of even digits from reversed credit card number
		int evenSum = calculateSumFromEvenDigits(reversedCreditCard);
		// if sum of the odd and even digits are either/both equal to -1, the reversed credit card number is incorrect
		if(oddSum == -1 || evenSum == -1) {
			return false;
		}
		// adds the odd and even sums together
		int sum = oddSum + evenSum;
		// gets the last digit of the sum 
		String sumStr = String.valueOf(sum);
		int lastPosOfSumStr = sumStr.length()-1;
		String lastDigit = String.valueOf(sumStr.charAt(lastPosOfSumStr));
		// returns a boolean "true" if the last digit of the sum is equal to 0 
		return lastDigit.equals("0");
		
	}
    

	public static void generateCustomerDataFile(String path, String fileName, StringBuffer strBuf) throws IOException {
		// generates the data file by calling write to file
		// checks if the path is null. if yes, put an empty string into path
		if (path == null) {
			path = "";
		}

		// checks if the file name is null. defaults the file name to "myFile.csv"
		if (fileName == null || fileName.isBlank()) {
			fileName = "myFile.csv";
		}

		// if path is not empty, checks whether the last character of the path is either "/" or "\". if not, add either to the end of the path. 
		if (!path.isBlank()) {
			if (path.contains("/") && path.lastIndexOf("/") != path.length()) {
				path = path + "/";
			} else if (path.contains("\\") && path.lastIndexOf("\\") != path.length()) {
				path = path + "\\";
			} else
				path = path + "/";
		}

		// calls writeToFile to persist to the file
		writeToFile(path, fileName, strBuf);

	}

	/*******************************************************************
	 * ADDITIONAL METHODS MAY BE ADDED BELOW IF NECESSARY *
	 *******************************************************************/

	private static String reverseOrder(String creditCard) {
		// reverses the order of the credit card numbers
		
		// checks if the credit card is null or empty. if yes, return empty string
		if (creditCard == null || creditCard.isBlank()) {
			return "";
		}
		// constructs a stringbuffer object to contain the credit card numbers
		StringBuffer buffer = new StringBuffer(creditCard);
		// reverses the stringbuffer
		StringBuffer reverse = buffer.reverse();
		// returns the reversed credit card numbers from the stringbuffer as strings
		return reverse.toString();
	}

	private static int calculateSumFromOddDigits(String reversedCreditCard) {
		// calculates the odd digits of the reversed credit card
		// checks if reversed credit card is null or empty. if yes, return -1
		if (reversedCreditCard == null || reversedCreditCard.isBlank()) {
			return -1;
		}
		// calculates the sum of reversed odd digits 
		int oddSum = 0;
		// this for loop counts increments by 2 starting from 0 to get odd digits out from the reversed credit card
		for (int i = 0; i < reversedCreditCard.length(); i = i + 2) {
			// gets each digit out from reversed credit card string
			String digit = String.valueOf(reversedCreditCard.charAt(i));
			Integer oddNum = Integer.valueOf(digit);
			// adds all odd digits together
			oddSum = oddSum + oddNum.intValue();
		}
		// returns the sum of odd digits
		return oddSum;

	}

	private static int calculateSumFromEvenDigits(String reversedCreditCard) {
		// calculates the even digits of the reversed credit card
		// checks if reversed credit card is null or empty. if yes, return -1
		if (reversedCreditCard == null || reversedCreditCard.isBlank()) {
			return -1;
		}
		// gets the length of the reversed credit card
		int creditCardLen = reversedCreditCard.length();
		// finds how many even digits are in the reversed credit card
		int numOfEven = creditCardLen / 2;
		Integer[] evenArray = new Integer[numOfEven];
		// places each credit card number into an array
		int count = 0;
		// this for loop counts increments by 2 starting from 1 to get even digits out from the reversed credit card
		for (int i = 1; i < reversedCreditCard.length(); i = i + 2) {
			// gets each digit out from reversed credit card string 
			String digit = String.valueOf(reversedCreditCard.charAt(i));
			// stores each digit into array
			evenArray[count] = Integer.valueOf(digit);
			// advances the position for the array
			count++;
		}
		for (int j = 0; j < count; j++) {
			// takes each credit card number out from the array and multiply by 2 
			Integer num = evenArray[j];
			num = num * 2;
			// checks if results are > 9
			if (num > 9) {
				String numStr = num.toString();
				Integer sumOfDigits = 0;
				// when the number > 9, splits the digits into an array and adds them together, then saves it back to the even array
				for (int k = 0; k < numStr.length(); k++) {
					String digit = String.valueOf(numStr.charAt(k));
					sumOfDigits = sumOfDigits + Integer.valueOf(digit);
				} // for k
				evenArray[j] = sumOfDigits;

			} // if num > 9
			else {
				// if number <= 9, place into array
				evenArray[j] = num;
			} // else
		} // for j

		// adds all even digits together 
		int evenSum = 0;
		for (int m = 0; m < count; m++) {
			evenSum = evenSum + evenArray[m];
		}
		// returns the sum of even digits
		return evenSum;
	}

	
	public static long generateId() throws IOException {
		// gets previously used id from file and adds 1 to get new id value 
		long id = getIdFromFile();
		id++;
		setIdToFile(id);
		return id;
	}
	
	
	private static long getIdFromFile() {
		// if the CustomerId file exists, read the number from the file
		Path path = Paths.get("C://ics3u//CustomerId.txt");
		String read = "";
		try {
			read = Files.readAllLines(path).get(0);
		} catch(IOException e) {
			// if the file does not exist, the id will be set to 0
			read = "0";
		}
		Long id = Long.valueOf(read);
		return id;
	}
	
	
	private static void setIdToFile(long id) throws IOException {
		// writes new id into the CustomerId file  
		long idFromFile = getIdFromFile();
		if (id > idFromFile) { 
			File outFile = new File("C://ics3u//CustomerId.txt");
		    outFile.getParentFile().mkdirs();
			PrintWriter out = new PrintWriter(outFile);
			out.println(String.valueOf(id));
			out.close();
		}
	}
	
	
	
	private static void writeToFile(String path, String fileName, StringBuffer strBuf) throws IOException {
		// writes customer information into CSV output file, if the file already exists, the information will be appended to the bottom of the file
		String pathFileName = path + fileName;
		File file = new File(pathFileName);
		boolean isFileExist = file.exists();
		file.getParentFile().mkdirs();
		FileWriter outFile = new FileWriter(file, true);
		PrintWriter out = new PrintWriter(outFile);
		// checks if file already exists. if the file already exists, header does not need to be written into the file. otherwise, a header will be included. 
		if (!isFileExist) {
			String header = "ID,FirstName,LastName,city,PostalCode,CreditCard";
			out.println(header);
		}
		// checks if the customer information is empty or null. if not, append to the file. 
		if (strBuf != null && strBuf.length() > 0) {
			out.println(strBuf.toString());
		}
		System.out.println("Done");
		out.close();
	}
}
