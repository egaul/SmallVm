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

    public void OUT(String output){
        System.out.println(output);
    }

    public void OUT(Descriptor var){
        System.out.println(var.getValue());
    }
}
