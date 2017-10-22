package Lab01;

public class Key {

    public final String str;
    public final byte num;

    public Key (String str, byte num){
        this.str = str;
        this.num = num;
    }

    public Key(){
        this.str = "";
        this.num = 0;
    }

    public String getStringKey(){
        return str;
    }

    public byte getNumKey(){
        return num;
    }

    @Override
    public String toString() {
        return str + " " + num;
    }
}
