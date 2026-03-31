/** Project: Lab4 Pizza
 * Purpose Details: This program sends and receives Pizza objects using RabbitMQ and Web Services
 * Course: IST 242
 * Author: Jason Mathew
 * Date Developed: 3/28/26
 * Last Date Changed: 3/31/26
 * Rev: 1.0

 */

package main;

public class Pizza {
    private String size;
    private String crust;
    private String topping;
    private double price;

    public Pizza() {
    }

    public Pizza(String size, String crust, String topping, double price) {
        this.size = size;
        this.crust = crust;
        this.topping = topping;
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCrust() {
        return crust;
    }

    public void setCrust(String crust) {
        this.crust = crust;
    }

    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "size='" + size + '\'' +
                ", crust='" + crust + '\'' +
                ", topping='" + topping + '\'' +
                ", price=" + price +
                '}';
    }
}
