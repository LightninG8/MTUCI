import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        String path1 = "./FirstDoc.txt";
        String path2 = "./SecondDoc.txt";

        File file1;
        File file2;

        try {
            file1 = new File(path1);
            file2 = new File(path2);

            FileWriter f1 = new FileWriter(file2, true);

            Scanner scanner = new Scanner(file1);
            ArrayList<String> arr = new ArrayList<>();
            while (scanner.hasNextLine()) {
                arr.add(scanner.nextLine());
            }
            scanner.close();

            for (int i = 0; i < arr.size(); i++) {
                f1.append(arr.get(i));
                f1.append("\n");
            }
            f1.flush();
            //System.out.println(arr);


        }
        catch(FileNotFoundException e) {
            System.out.println("You should properly choose your files");
        }
        catch(IOException f) {
            System.out.println("Something wrong with your inout and output");
        }



    }
}
