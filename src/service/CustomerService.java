package service;

import model.Customer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CustomerService {
   private static Set<Customer> customersSet =  new HashSet<Customer>();

    public void addCustomer(String email, String firstName, String lastName){}

    public Customer getCustomer(String customerEmail){
        return null;
    }

    public Collection<Customer> getAllCustomers(){
        return null;
    }
}
