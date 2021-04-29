package sample;

import java.io.Serializable;

public class Satelite implements Serializable {
    public int code;
    public int distance;
    public String name;


    public Satelite(int code, String name, int distance){
        this.code = code;
        this.name = name;
        this.distance = distance;
    }
}
