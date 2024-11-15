package com.sd.sdzx.manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
@Data

@AllArgsConstructor
public class Product {
    private int id;
    private double price;
    private int quantity;

//
//    public int getId() {
//        return id;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }

    public double getTotalPrice() {
        return price * quantity;
    }

//    public static void main(String[] args) {
//        List<Product> products = new ArrayList<>();
//        products.add(new Product(1, 100.0, 5));
//        products.add(new Product(2, 220.0, 10));
//        products.add(new Product(3, 303.0, 15));
//
//        double totalSales = products.stream()
//                .mapToDouble(Product::getTotalPrice)
//                .sum();
//        int totalQuantity = products.stream()
//                .mapToInt(Product::getQuantity)
//                .sum();
//
//        System.out.println("总销售额: " + totalSales);
//        System.out.println("总销售量: " + totalQuantity);
//    }
public static void main(String[] args) {

        List<Product> products = new ArrayList<>();
        products.add(new Product(1, 10.0, 5));
        products.add(new Product(2, 20.0, 10));
        products.add(new Product(3, 30.0, 15));
        products.add(new Product(4, 120.0, 8));
        products.add(new Product(5, 150.0, 12));

        double totalSales = products.stream()
                .filter(p -> p.getPrice() > 100.0)
                .mapToDouble(Product::getTotalPrice)
                .sum();
        int totalQuantity = products.stream()
                .filter(p -> p.getPrice() > 100.0)
                .mapToInt(Product::getQuantity)
                .sum();

        System.out.println("Total sales of products with price > 100: " + totalSales);
        System.out.println("Total quantity of products with price > 100: " + totalQuantity);
    }
}

