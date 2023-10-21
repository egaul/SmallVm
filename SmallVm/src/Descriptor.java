import java.util.ArrayList;

public class Descriptor {

    private int value;
    private String name;

    public Descriptor(String name, int value){
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
