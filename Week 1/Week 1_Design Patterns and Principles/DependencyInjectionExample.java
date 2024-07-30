interface CustomerRepository{
    void findCustomerById(int id);
}

class CustomerRepositoryImpl implements CustomerRepository{
    public void findCustomerById(int id) {
        System.out.println("Finding customer with id " + id);
    }
}

class CustomerService{
    private CustomerRepository repo;
    CustomerService(CustomerRepository repo){
        this.repo = repo;
    }
    public void findCustomerById(int id) {
        repo.findCustomerById(id);
    }
}

class Main{
    public static void main(String[] args) {
        CustomerRepository repo = new CustomerRepositoryImpl();
        CustomerService service = new CustomerService(repo);
        service.findCustomerById(100);
    }
}