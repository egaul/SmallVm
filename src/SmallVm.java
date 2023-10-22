import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.String.valueOf;

public class SmallVm {

    private final String author = "Eliana Gaul, CSCI 4200, Fall 2023";

    private static final int MAX_MEMORY_SIZE = 1000;

    //Stores instructions to execute
    private ArrayList<String> instructions = new ArrayList<>(MAX_MEMORY_SIZE);

    //Stores output to write out to txt file
    private ArrayList<String> output = new ArrayList<>(MAX_MEMORY_SIZE);


    //Stores descriptors of variables
    private ArrayList<Descriptor> descriptors = new ArrayList<>();

    //Initialize Method object to execute basic methods
    private Operation opExe = new Operation();

    private boolean goProg = true;

    public SmallVm() {

    }

    public static void main(String[] args) throws Exception {

        SmallVm vm = new SmallVm();
        vm.run();
    }

    public void run() throws Exception{

        this.pullInstructions();

        while (goProg) {
            //Iterates through each instruction in the instructions list
            for (String instruct : instructions) {

                //Splits instruction into String array of 2 strings for easier interpretation
                String[] args = instruct.split(" ", 2);

                //The first element holds the operation to execute
                String cmd = args[0];

                if (cmd.equals("OUT")) {
                    this.cmdOut(args[1]);
                } else if (cmd.equals("IN")) {
                    this.cmdIn(args[1]);
                } else if (cmd.equals("ADD")) {
                    this.cmdAdd(args[1]);
                } else if (cmd.equals("SUB")) {
                    this.cmdSub(args[1]);
                } else if (cmd.equals("DIV")) {
                    this.cmdDiv(args[1]);
                } else if (cmd.equals("MUL")) {
                    this.cmdMul(args[1]);
                } else if (cmd.equals("STO")) {
                    this.cmdSto(args[1]);
                } else if (cmd.equals("HALT")) {
                    this.pushOutput();
                    this.goProg = false;
                }
            }
        }
    }

    private void cmdAdd(String inputs) {
        String[] input = inputs.split(" ", 3);
        String name = input[0];
        String strNum1 = input[1];
        String strNum2 = input[2];
        int num1 = getNum(strNum1);
        int num2 = getNum(strNum2);

        //calculates sum value
        int value = num1 + num2;

        //Add new descriptor to list
        descriptors.add(new Descriptor(name, value));
    }

    private void cmdSub(String inputs) {
        String[] input = inputs.split(" ", 3);
        String name = input[0];
        String strNum1 = input[1];
        String strNum2 = input[2];
        int num1 = getNum(strNum1);
        int num2 = getNum(strNum2);

        //calculates difference value
        int value = num1 - num2;

        //Add new descriptor to list
        descriptors.add(new Descriptor(name, value));
    }

    private void cmdDiv(String inputs) {
        String[] input = inputs.split(" ", 3);
        String name = input[0];
        String strNum1 = input[1];
        String strNum2 = input[2];
        int num1 = getNum(strNum1);
        int num2 = getNum(strNum2);

        //calculates quotient value
        int value = num1 / num2;

        //Add new descriptor to list
        descriptors.add(new Descriptor(name, value));
    }

    private void cmdMul(String inputs) {
        String[] input = inputs.split(" ", 3);
        String name = input[0];
        String strNum1 = input[1];
        String strNum2 = input[2];
        int num1 = getNum(strNum1);
        int num2 = getNum(strNum2);

        //calculates product value
        int value = num1 * num2;

        //Add new descriptor to list
        descriptors.add(new Descriptor(name, value));
    }

    private int getNum(String strNum) {
        int num;
        if (opExe.isNumeric(strNum)) {
            //Checks if string contains literal int
            num = Integer.parseInt(strNum);
        } else {
            //If not, finds descriptor and assigns value to num
            int varIndex = opExe.findVar(descriptors, strNum);
            num = descriptors.get(varIndex).getValue();
        }
        return num;
    }

    private void cmdIn(String name) {
        Scanner in = new Scanner(System.in);
        int value = in.nextInt();
        descriptors.add(new Descriptor(name, value));
        output.add(valueOf(value));
    }

    private void cmdOut(String input) {
        if (opExe.isVar(descriptors, input)) {
            //Checks if input is a variable, if so then find it and print value stored in descriptor object
            int varIndex = opExe.findVar(descriptors, input);
            System.out.println(descriptors.get(varIndex).getValue());
            String out = valueOf(descriptors.get(varIndex).getValue());
            output.add(out);

        } else {
            //prints string without double quotes
            System.out.println(input.replace("\"", ""));
            output.add(input.replace("\"", ""));
        }
    }

    private void cmdSto(String inputs){
        //Stores either variable int value or literal int value into new Descriptor
        String[] input = inputs.split(" ", 2);
        String name = input[0];
        String strValue = input[1];
        int intVal = getNum(strValue);

        //Add new descriptor to list
        descriptors.add(new Descriptor(name, intVal));
    }

    private void pullInstructions() throws IOException {
        // Open the main file
        FileInputStream fileIn = new FileInputStream("mySmallVm_Prog.txt");
        //Open test file 1
        //FileInputStream fileIn = new FileInputStream("IN_OUT_ADD_test.txt");
        //Open test file 2
        //FileInputStream fileIn = new FileInputStream("IN_OUT_SUB_test.txt");
        //Open test file 3
        //FileInputStream fileIn = new FileInputStream("IN_OUT_DIV_test.txt");
        //Open test file 4
        //FileInputStream fileIn = new FileInputStream("IN_OUT_MUL_test.txt");

        BufferedReader br = new BufferedReader(new InputStreamReader(fileIn));

        String line;
        instructions.add(author);
        instructions.add("****************************************");
        //Read File Line By Line
        while ((line = br.readLine()) != null) {
            // Stores instructions in arraylist
            instructions.add(line);
        }
        //Close the input stream
        fileIn.close();

        //Prepping output
        output.addAll(instructions);
    }

    private void pushOutput() throws Exception{
        PrintWriter writer = new PrintWriter("mySmallVm_Output.txt", StandardCharsets.UTF_8);
        for (String line: output) {
            writer.println(line);
            if (line.equals("HALT")){
                writer.print("****************************************");
            }
        }
        writer.close();
    }
}