package dealership.model;

public class SalesContract {
    private int id;
    private String vin;
    private String customerName;
    private double salePrice;

    public SalesContract(int id, String vin, String customerName, double salePrice) {
        this.id = id;
        this.vin = vin;
        this.customerName = customerName;
        this.salePrice = salePrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }
}

