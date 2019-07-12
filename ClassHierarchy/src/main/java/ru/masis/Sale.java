package ru.masis;

public class Sale {
    private String item;
    private Double price;

    public Sale(String item, Double price) {
        this.item = item;
        this.price = price;
    }

    public String getItem() {
        return item;
    }

    public Double getPrice() {
        return price;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "item='" + item + '\'' +
                ", price=" + price +
                '}';
    }
}

