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
        ArrayList<String> instructions = new ArrayList<>(MAX_MEMORY_SIZE);

        //Stores descriptors of variables
        ArrayList<Descriptor> descriptors = new ArrayList<>();

        //Initialize Method object to execute basic methods
        Operation opExe = new Operation();

        // Open the main file
        //FileInputStream fileIn = new FileInputStream("mySmallVm_Prog.txt");
        //Open test file 1
        //FileInputStream fileIn = new FileInputStream("IN_OUT_ADD_test.txt");
        //Open test file 2
        //FileInputStream fileIn = new FileInputStream("IN_OUT_SUB_test.txt");
        //Open test file 3
        FileInputStream fileIn = new FileInputStream("IN_OUT_DIV_test.txt");
        //Open test file 4
        //FileInputStream fileIn = new FileInputStream("IN_OUT_MUL_test.txt");

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
            //Iterates through each instruction in the instructions list
            for (String instruct: instructions) {

                //Splits instruction into String array of individual words/inputs for easier interpretation
                String[] inputs = instruct.split(" ");

//                //Test print inputs
//                for (String part: inputs) {
//                    System.out.print("["+part+"]");
//                }
//                System.out.println();

                //The first element represents the operation to execute
                String cmd = inputs[0];

                if(cmd.equals("OUT")){

                    String input = inputs[1];

                    if (opExe.isVar(descriptors, input)){

                        int varIndex = opExe.findVar(descriptors, input);

                        System.out.println(descriptors.get(varIndex).getValue());

                    }
                    else {
                        for(int i = 1; i< inputs.length; i++){
                            System.out.print(inputs[i]+" ");
                        }
                        System.out.println();//new line for output readability
                    }
                }

                else if (cmd.equals("IN")) {

                    String name = inputs[1];

                    int value = in.nextInt();

                    descriptors.add(new Descriptor(name,value));

                }

                else if (cmd.equals("ADD")) {

                    String name = inputs[1];

                    String strNum1 = inputs[2];

                    String strNum2 = inputs[3];

                    int num1;

                    int num2;

                    //Checks if string contains literal int
                    if (opExe.isNumeric(strNum1)){

                        num1 = Integer.parseInt(strNum1);

                    }
                    //If not, finds descriptor and assigns value to num1
                    else{

                        int varIndex = opExe.findVar(descriptors, strNum1);

                        num1 = descriptors.get(varIndex).getValue();

                    }
                    //Checks if string contains literal int
                    if (opExe.isNumeric(strNum2)){

                        num2 = Integer.parseInt(strNum2);

                    }
                    //If not, finds descriptor and assigns value to num2
                    else{

                        int varIndex = opExe.findVar(descriptors, strNum2);

                        num2 = descriptors.get(varIndex).getValue();

                    }

                    //calculates sum value
                    int value = num1 + num2;

                    //Adds new variable descriptor
                    descriptors.add(new Descriptor(name,value));

                }
                else if (cmd.equals("SUB")) {

                    String name = inputs[1];

                    String strIn1 = inputs[2];

                    String strIn2 = inputs[3];

                    int num1;

                    int num2;

                    //Checks if string contains literal int
                    if (opExe.isNumeric(strIn1)){

                        num1 = Integer.parseInt(strIn1);

                    }
                    //If not, finds descriptor and assigns value to num1
                    else{

                        int varIndex = opExe.findVar(descriptors, strIn1);

                        num1 = descriptors.get(varIndex).getValue();

                    }

                    //Checks if string contains literal int
                    if (opExe.isNumeric(strIn2)){

                        num2 = Integer.parseInt(strIn2);

                    }
                    //If not, finds descriptor and assigns value to num2
                    else{

                        int varIndex = opExe.findVar(descriptors, strIn2);

                        num2 = descriptors.get(varIndex).getValue();

                    }
                    //calculates difference value
                    int value = num1 - num2;

                    //Adds new variable descriptor
                    descriptors.add(new Descriptor(name,value));

                }
                else if (cmd.equals("DIV")) {

                    String name = inputs[1];

                    String strIn1 = inputs[2];

                    String strIn2 = inputs[3];

                    int num1;

                    int num2;

                    //Checks if string contains literal int
                    if (opExe.isNumeric(strIn1)){

                        num1 = Integer.parseInt(strIn1);

                    }
                    //If not, finds descriptor and assigns value to num1
                    else{

                        int varIndex = opExe.findVar(descriptors, strIn1);

                        num1 = descriptors.get(varIndex).getValue();

                    }
                    //Checks if string contains literal int
                    if (opExe.isNumeric(strIn2)){

                        num2 = Integer.parseInt(strIn2);

                    }
                    //If not, finds descriptor and assigns value to num2
                    else{

                        int varIndex = opExe.findVar(descriptors, strIn2);

                        num2 = descriptors.get(varIndex).getValue();

                    }

                    //calculates quotient value
                    int value = num1 / num2;

                    //Adds new variable descriptor
                    descriptors.add(new Descriptor(name,value));

                }
                else if (cmd.equals("MUL")) {

                    String name = inputs[1];

                    String strIn1 = inputs[2];

                    String strIn2 = inputs[3];

                    int num1;

                    int num2;

                    //Checks if string contains literal int
                    if (opExe.isNumeric(strIn1)){

                        num1 = Integer.parseInt(strIn1);

                    }
                    //If not, finds descriptor and assigns value to num1
                    else{

                        int varIndex = opExe.findVar(descriptors, strIn1);

                        num1 = descriptors.get(varIndex).getValue();

                    }
                    //Checks if string contains literal int
                    if (opExe.isNumeric(strIn2)){

                        num2 = Integer.parseInt(strIn2);

                    }
                    //If not, finds descriptor and assigns value to num2
                    else{

                        int varIndex = opExe.findVar(descriptors, strIn2);

                        num2 = descriptors.get(varIndex).getValue();

                    }

                    //calculates difference value
                    int value = num1 * num2;

                    //Adds new variable descriptor
                    descriptors.add(new Descriptor(name,value));

                }
                else if (cmd.equals("HALT")) {

                    goProg = false;

                }
            }
        }
//        for (Descriptor d: descriptors) {
//            System.out.println("name: "+d.getName()+", value: "+d.getValue());
//        }
    }
}