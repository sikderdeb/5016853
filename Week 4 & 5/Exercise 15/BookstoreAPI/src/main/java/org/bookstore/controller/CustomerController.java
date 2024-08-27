package org.bookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.bookstore.entity.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final List<Customer> customers = new ArrayList<>();

    // POST endpoint to create a new customer using a JSON request body
    @Operation(summary = "Add a new customer", description = "Add a new customer to the bookstore")
    @ApiResponse(responseCode = "201", description = "Customer added successfully")
    @ApiResponse(responseCode = "400", description = "Invalid customer data")
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public void createCustomer(@Valid @RequestBody Customer customer) {
        customers.add(customer);
    }

    // POST endpoint to process form data for customer registration
    @Operation(summary = "Add a new customer", description = "Add a new customer to the bookstore")
    @ApiResponse(responseCode = "201", description = "Customer added successfully")
    @ApiResponse(responseCode = "400", description = "Invalid customer data")
    @PostMapping(value = "/registrations", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public void registerCustomer(@RequestParam("name") String name,
                                 @RequestParam("email") String email,
                                 @RequestParam("address") String address) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setAddress(address);
        customers.add(customer);
    }

    // GET endpoint to retrieve all customers
    @Operation(summary = "Get all customers", description = "Retrieve a list of all customers")
    @ApiResponse(responseCode = "200", description = "Customers retrieved successfully")
    @ApiResponse(responseCode = "404", description = "No customers found")
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<EntityModel<List<Customer>>> getCustomers() {
        List<Customer> customerList = customers;

        // Add self-link to the list of customers
        Link selfLink = linkTo(methodOn(CustomerController.class).getCustomers()).withSelfRel();

        EntityModel<List<Customer>> customersModel = EntityModel.of(customerList, selfLink);

        return new ResponseEntity<>(customersModel, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @Operation(summary = "Get customer by ID", description = "Retrieve a customer by its ID")
    @ApiResponse(responseCode = "200", description = "Customer found successfully")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    public ResponseEntity<EntityModel<Customer>> getCustomerById(@PathVariable("id") int id) {
        for (Customer customer : customers) {
            if (customer.getId().equals(id)) {
                // Create a link to the current customer resource
                Link selfLink = linkTo(methodOn(CustomerController.class).getCustomerById(id)).withSelfRel();
                // Create a link to the collection of customers
                Link customersLink = linkTo(methodOn(CustomerController.class).getCustomers()).withRel("all-customers");

                EntityModel<Customer> customerModel = EntityModel.of(customer, selfLink, customersLink);

                return new ResponseEntity<>(customerModel, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    // PUT endpoint to update an existing customer using optimistic locking
    @Operation(summary = "Update a customer", description = "Update an existing customer")
    @ApiResponse(responseCode = "200", description = "Customer updated successfully")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    @PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<String> updateCustomer(@Valid @RequestBody Customer customer, @PathVariable("id") int id) {
        for (Customer oldCustomer : customers) {
            if (oldCustomer.getId().equals(id)) {
                if (!oldCustomer.getVersion().equals(customer.getVersion())) {
                    return new ResponseEntity<>("Customer has been modified by another transaction", HttpStatus.CONFLICT);
                }
                oldCustomer.setName(customer.getName());
                oldCustomer.setEmail(customer.getEmail());
                oldCustomer.setAddress(customer.getAddress());
                return new ResponseEntity<>("Customer updated successfully", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
    }

    // DELETE endpoint to remove a customer
    @Operation(summary = "Delete a customer", description = "Delete a customer by its ID")
    @ApiResponse(responseCode = "202", description = "Customer deleted successfully")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") int id) {
        customers.removeIf(customer -> customer.getId().equals(id));
        return new ResponseEntity<>("Customer deleted successfully", HttpStatus.NO_CONTENT);
    }
}
