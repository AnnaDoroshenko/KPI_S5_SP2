package Lab01;

public class Row {

    private Key key;
    private float functionalField;
    private boolean isDeleted;

    public Row (Key key, float functionalField){
        this.key = key;
        this.functionalField = functionalField;
        this.isDeleted = false;
    }

    public Row (){
        this.key = new Key();
        this.functionalField = 0;
        this.isDeleted = false;
    }

    public void markDeleted(){
        isDeleted = true;
    }

    public Key getKey(){
        return key;
    }

    public boolean getDeleted(){
        return isDeleted;
    }

    public float getField() {
        return functionalField;
    }

    @Override
    public String toString() {
        return key + " " + functionalField + "\n";
    }
}
