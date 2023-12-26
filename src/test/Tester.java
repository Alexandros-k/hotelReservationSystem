package test;

import model.Customer;

public class Tester {
    public static void main(String[] args) {
        Customer customer = new Customer(1,"first", "second", "j@domain.com");
        Customer customer1 = new Customer(2,"first", "second", "email");

        System.out.println(customer1);
    }
}
