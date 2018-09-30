public class Beer {


    private String name;
    private String style;
    private double strength;

    Beer(String name, String style, double strength){
        this.name = name;
        this.style = style;
        this.strength = strength;
    }

    public String toString(){
     return this.name + " " + this.style + " " + this.strength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }



}
