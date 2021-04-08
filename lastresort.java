import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
class lastresort {
    public static void main(String[] args) throws IOException {
        String filename = "postal_codes.csv";
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        int num = -1;
        int count = 0;
        while (line != null){
            count++;
            line = reader.readLine();
            if (line != null){
                String linechar3 = line.substring(0,3);
                if (linechar3.equals("Pos")){
                    num = 1;
                    break;
                }
            }
        }
        System.out.println(count);
        System.out.println(line);
        if (num < 0){
            System.out.println("Postal Code is not valid");
            count = 100;
        }
        else {
            System.out.println("Postal Code is valid");
        }
        count = 100;
        count = 100;
        System.out.println(count);
        reader.close();
    }
}
