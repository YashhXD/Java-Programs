class vehicle {
    int speed;
    String color;
    
    public vehicle(int speed, String color){
        this.speed=speed;
        this.color=color;
    }
    
    public void display(){
        System.out.println("Speed : "+speed);
        System.out.println("Color : "+color);
    }
}
class car extends vehicle{
    int numDoors;
    public car(int speed, String color, int numDoors) {
        super(speed, color);
        this.numDoors = numDoors;
    }
    public void display(){
        super.display();
        System.out.println("Doors : "+this.numDoors);
    }
}
public class details{
    public static void main(String[] args){
        car c1 = new car(240,"dark green",2);
        c1.display();
        vehicle v1 = new vehicle(99, "purple");
        v1.display();
    }
}
