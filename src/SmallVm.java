import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.String.valueOf;

public class SmallVm {

    //initialize memory size
    private static final int MAX_MEMORY_SIZE = 1000;

    //Stores instructions to execute
    private ArrayList<String> instructions = new ArrayList<>(MAX_MEMORY_SIZE);

    //Stores output to write out to txt file
    private ArrayList<String> output = new ArrayList<>(MAX_MEMORY_SIZE);

    //Stores descriptors of variables
    private ArrayList<Descriptor> descriptors = new ArrayList<>(MAX_MEMORY_SIZE);

    //holds boolean value true while cmd does not equal HALT
    private boolean goProg = true;

    public SmallVm() {
        //Empty constructor
    }

    public static void main(String[] args) throws Exception {
        SmallVm vm = new SmallVm();
        vm.run();
    }

    public void run() throws Exception{
        //while goProg does not reach cmd HALT the run method will keep decoding and executing.

        this.load();

        while (goProg) {

            for (String instruct : instructions) {
                //Iterates through each instruction line in the instructions list

                //Trims instruction
                String lineInput = instruct.trim();

                //Skips instruction if they start with ";" comments
                if(lineInput.startsWith(";")){
                    continue;
                }

                //Splits instruction into String array of 2 strings for easier interpretation
                String[] cleanInput = lineInput.split(" ", 2);

                System.out.println("args: "+ Arrays.toString(cleanInput));

                //The first element holds the operation to execute
                String cmd = cleanInput[0];
                String args = null;

                //Checks if
                if (cleanInput.length > 1) {
                    args = cleanInput[1];
                }

                //If statements to decode then execute
                if (cmd.equals("OUT")) {
                    this.cmdOut(args);
                } else if (cmd.equals("IN")) {
                    this.cmdIn(args);
                } else if (cmd.equals("ADD")) {
                    this.cmdAdd(args);
                } else if (cmd.equals("SUB")) {
                    this.cmdSub(args);
                } else if (cmd.equals("DIV")) {
                    this.cmdDiv(args);
                } else if (cmd.equals("MUL")) {
                    this.cmdMul(args);
                } else if (cmd.equals("STO")) {
                    this.cmdSto(args);
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
        this.descriptors.add(new Descriptor(name, value));
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
        this.descriptors.add(new Descriptor(name, value));
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
        this.descriptors.add(new Descriptor(name, value));
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
        this.descriptors.add(new Descriptor(name, value));
    }

    private int getNum(String strNum) {
        int num;
        if (isNumeric(strNum)) {
            //Checks if string contains literal int then converts
            num = Integer.parseInt(strNum);
        } else {
            //If not, finds descriptor and assigns value to num
            int varIndex = findVar(strNum);
            num = this.descriptors.get(varIndex).getValue();
        }
        return num;
    }

    private boolean isVar (String name){
        //Checks if name matches one of the descriptors name value
        for (Descriptor var : this.descriptors) {
            if (var.getName().equals(name)){
                return true;//returns true if there is a descriptor of a variable holding name value
            }
        }
        return false;//returns false if otherwise
    }

    private int findVar (String name){
        //
        int varIndex = 0;

        for (Descriptor var : this.descriptors) {
            if (var.getName().equals(name)){
                varIndex = this.descriptors.indexOf(var);
            }
        }
        return varIndex;
    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+");  //match a number with optional '-' and decimal.
    }

    private void cmdIn(String name) {
        //Takes user input and initializes descriptor
        Scanner in = new Scanner(System.in);
        int value = in.nextInt();

        //Adds new descriptor to list
        this.descriptors.add(new Descriptor(name, value));
        //Adds user input to output list
        this.output.add(valueOf(value));
    }

    private void cmdOut(String input) {
        if (isVar(input)) {
            //Checks if input is a variable, if so then find it and print value stored in descriptor object
            int varIndex = findVar(input);
            System.out.println(this.descriptors.get(varIndex).getValue());
            String out = valueOf(this.descriptors.get(varIndex).getValue());
            this.output.add(out);

        } else {
            //prints string without double quotes
            System.out.println(input.replace("\"", ""));
            this.output.add(input.replace("\"", ""));
        }
    }

    private void cmdSto(String inputs){
        //Stores either variable int value or literal int value into new Descriptor
        String[] input = inputs.split(" ", 2);
        String name = input[0];
        String strValue = input[1];
        int intVal = getNum(strValue);

        //Add new descriptor to list
        this.descriptors.add(new Descriptor(name, intVal));
    }

    private void load() throws IOException {
        //Opens file, fetches each line, then stores instruction in array list
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
        String author = "Eliana Gaul, CSCI 4200, Fall 2023";

        //Read File Line By Line
        while ((line = br.readLine()) != null) {
            // Stores instructions in arraylist
            this.instructions.add(line);
        }
        //Close the input stream
        fileIn.close();

        //Prepping initial output
        this.output.add(author);
        this.output.add("****************************************");
        this.output.addAll(instructions);
    }

    private void pushOutput() throws Exception{
        //Creates and writes into an output txt file
        PrintWriter writer = new PrintWriter("mySmallVm_Output.txt", StandardCharsets.UTF_8);
        for (String line: this.output) {
            writer.println(line);
            if (line.equals("HALT")){
                writer.print("****************************************");
            }
        }
        writer.close();
    }
}