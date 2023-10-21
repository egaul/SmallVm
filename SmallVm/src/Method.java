

public class Method {
    public Method(){
       //Empty constructor
    }
    public int ADD(int num1, int num2){
        return num1 + num2;
    }

    public int SUB(int num1, int num2){
        return num1 - num2;
    }

    public int MUL(int num1, int num2){
        return num1 * num2;
    }

    public int DIV(int num1, int num2){
        return num1 / num2;
    }

    public Descriptor IN(String name, int value){
        return new Descriptor(name,value);
    }

    public void OUT(String[] output){
        for(int i = 1; i< output.length; i++){
            System.out.print(output[i]);
        }
        System.out.println();//new line for output readability
    }

    public void OUT(Descriptor var){
        System.out.println(var.getValue());
    }

    public boolean isNumeric(String str) {
        return str.matches("-?\\d");  //matches both negative and positive integers.
    }
}
