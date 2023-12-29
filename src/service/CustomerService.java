package service;

import model.Customer;

import java.util.*;

public class CustomerService {

   private static CustomerService customerService;
   private static List<Customer> customerList = new ArrayList<Customer>();

  private CustomerService(){}

  public static CustomerService getInstance(){
      customerService = new CustomerService();
      return customerService;
  }

    public void addCustomer(String email, String firstName, String lastName){
      customerList.add(new Customer(email, firstName, lastName));
    }

    public Customer getCustomer(String customerEmail){
        for (Customer customer : customerList) {
            if (customer.getEmail().equals(customerEmail)) {
                return customer;
            }
        }
      return null;
    }

    public Collection<Customer> getAllCustomers(){
        return customerList;
    }
}
