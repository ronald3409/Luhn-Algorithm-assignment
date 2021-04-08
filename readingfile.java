import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
class readingfile {
    public static void main(String[] args) throws FileNotFoundException {
        String filename = "postal_codes.csv";
        File textfile = new File(filename);
        Scanner reader = new Scanner(textfile);
        int count = 0;
        String line = " ";
            while(reader.hasNext()){
                line = reader.nextLine();
                System.out.println(line);
                count ++;
            }
        System.out.println(count);
        reader.close();
    }
}
