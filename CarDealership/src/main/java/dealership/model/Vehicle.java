package dealership.model;

public class Vehicle {
    private int price;
    private int year;
    private String color;
    private String type;

    public Vehicle() {
    }

    public Vehicle(int price, int year, String color, String type) {
        this.price = price;
        this.year = year;
        this.color = color;
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "price=" + price +
                ", year=" + year +
                ", color='" + color + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
