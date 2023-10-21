import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class SmallVm {
    public static void main(String[] args) throws Exception {

        final int MAX_MEMORY_SIZE = 1000;

        Scanner in = new Scanner(System.in);

        //Stores instructions to execute
        ArrayList<String> instructions = new ArrayList<String>(MAX_MEMORY_SIZE);

        //Stores descriptors of variables
        ArrayList<Descriptor> descriptors = new ArrayList<Descriptor>();

        //Initialize Method object to execute basic methods
        Method execute = new Method();

        // Open the file
        //FileInputStream fileIn = new FileInputStream("mySmallVm_Prog.txt");
        //Open test file 1
        FileInputStream fileIn = new FileInputStream("IN_OUT_ADD_test.txt");

        BufferedReader br = new BufferedReader(new InputStreamReader(fileIn));

        String line;

        //Read File Line By Line
        while ((line = br.readLine()) != null)   {
            // Print the content on the console
            instructions.add(line);
        }
        //Close the input stream
        fileIn.close();
        
        boolean goProg = true;
        
        while(goProg){
            //Iterates through each individual line instruction
            for (String instruction: instructions) {

                //Splits instruction into String array of individual words/parts for easier command interpretation
                String[] parts = instruction.split(" ");

                //Test print parts
                for (String part: parts) {
                    System.out.print("["+part+"]");
                }
                System.out.println();

//                if(parts[0].equals("OUT")){
//
//                    execute.OUT(parts);
//
//                }
//
//                else if (parts[0].equals("IN")) {
//
//                    String name = parts[1];
//
//                    int value = in.nextInt();
//
//                    descriptors.add(execute.IN(name, value));
//                }
//
//                else if (parts[0].equals("ADD")) {
//
//                    String name = parts[1];
//
//                    int num1 = 0;
//
//                    int num2 = 0;
//
//                    //Checks if string contains int
//                    if (execute.isNumeric(parts[2])){
//
//                        num1 = Integer.parseInt(parts[2]);
//
//                    }
//                    //if not int, finds descriptor with name
//                    else{
//                        for (Descriptor descriptor : descriptors) {
//
//                            if (descriptor.getName().equals(parts[2])){
//                                num1 = descriptor.getValue();
//                            }
//                        }
//                    }
//                    //Checks if string contains int
//                    if (execute.isNumeric(parts[3])) {
//
//                        num2 = Integer.parseInt(parts[3]);
//
//                    }
//                    //if not int, finds descriptor with name
//                    else{
//                        for (Descriptor descriptor : descriptors) {
//
//                            if (descriptor.getName().equals(parts[3])) {
//                                num2 = descriptor.getValue();
//                            }
//                        }
//                    }
//
//                    int value = execute.ADD(num1, num2);
//
//                    descriptors.add(execute.IN(name, value));
//                }
//                else if (parts[0].equals("HALT")) {
//
//                    goProg = false;
//
//                }
            }
            goProg = false;
        }
    }
}