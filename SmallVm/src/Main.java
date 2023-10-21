import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {

        final int MAX_MEMORY_SIZE = 1000;

        //Stores instructions to execute
        ArrayList<String> instruction = new ArrayList<String>(MAX_MEMORY_SIZE);

        //Stores descriptors of variables
        ArrayList<Descriptor> descriptors = new ArrayList<Descriptor>();

        // Open the file
        FileInputStream fileIn = new FileInputStream("mySmallVm_Prog.txt");

        BufferedReader br = new BufferedReader(new InputStreamReader(fileIn));

        String line;

        //Read File Line By Line
        while ((line = br.readLine()) != null)   {
            // Print the content on the console
            instruction.add(line);
        }
        //Close the input stream
        fileIn.close();





    }
}