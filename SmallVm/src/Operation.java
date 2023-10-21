import java.util.ArrayList;

public class Operation {
    public Operation(){
       //Empty constructor
    }
    public boolean isVar (ArrayList<Descriptor> varList, String varID){

        for (Descriptor var : varList) {
            if (var.getName().equals(varID)){
                return true;
            }
        }
        return false;
    }
    public int findVar (ArrayList<Descriptor> varList, String varID){

        int varIndex = 0;

        for (Descriptor var : varList) {
            if (var.getName().equals(varID)){
                varIndex = varList.indexOf(var);
            }
        }
        return varIndex;
    }
    public boolean isNumeric(String str) {
        return str.matches("-?\\d");  //matches both negative and positive integers.
    }
}
