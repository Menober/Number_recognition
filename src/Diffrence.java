public class Diffrence {
    String name;
    double value;
    public Diffrence(String name,double value){
        this.name=name;
        this.value=value;
    }

    @Override
    public String toString() {
        String string;
        string="Name: "+name+" Value:"+value;
        return string;
    }
}
