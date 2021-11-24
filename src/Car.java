
public class Car {
    String carID;
    Brand brand;
    String color;
    String frameID;
    String engineID;

    public Car() {
        
    }

    public Car(String carID, Brand brand, String color, String frameID, String engineID) {
        this.carID = carID;
        this.brand = brand;
        this.color = color;
        this.frameID = frameID;
        this.engineID = engineID;
    }
  
    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID.toUpperCase();
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFrameID() {
        return frameID;
    }

    public void setFrameID(String frameID) {
        this.frameID = frameID.toUpperCase();
    }

    public String getEngineID() {
        return engineID;
    }

    public void setEngineID(String engineID) {
        this.engineID = engineID.toUpperCase();
    }

    @Override
    public String toString() {
        return carID  + ", " + brand.brandID +  ", " + color + ", " + frameID + ", " + engineID;
    }
    
    public String screenString(){
        return brand + "\n" + carID + ", " + color + ", " + frameID + ", " + engineID;
    }
    
    public int comparedTo(Car c){
        int d = this.brand.brandName.compareTo(c.brand.brandName);
        if (d!= 0){
            return d;
        }
        return this.carID.compareTo(c.carID);
    }
    
   
}
