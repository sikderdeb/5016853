package org.bookstore.controller;

import org.bookstore.entity.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final List<Customer> customers = new ArrayList<>();

    // POST endpoint to create a new customer using a JSON request body
    @PostMapping
    public void createCustomer(@RequestBody Customer customer) {
        customers.add(customer);
    }

    // POST endpoint to process form data for customer registration
    @PostMapping("/register")
    public void registerCustomer(@RequestParam("name") String name,
                                 @RequestParam("email") String email,
                                 @RequestParam("address") String address) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setAddress(address);
        customers.add(customer);
    }

    // Additional endpoint to retrieve all customers (optional, for testing)
    @GetMapping
    public List<Customer> getCustomers() {
        return customers;
    }
}
