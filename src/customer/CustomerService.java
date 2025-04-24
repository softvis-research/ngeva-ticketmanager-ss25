package customer;

import idgenerator.IDService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class CustomerService {
    public CustomerService() {
        this.customers = new ArrayList<>();
        this.idService = new IDService();
    }

    public long createCustomer(String userName, String emailAddress, LocalDate birthDate) {
        if (!birthDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("The birth date must be in the past!");
        }

        if (!birthDate.isBefore(LocalDate.now().minusYears(MINIMUM_AGE)))
            throw new IllegalArgumentException("The user must be at least 18 years old!");

        if (isEmailAddressInvalid(emailAddress))
            throw new IllegalArgumentException("The email address is not valid!");

        long customerID = idService.generateId();
        customers.add(new Customer(customerID, userName, emailAddress, birthDate));

        return customerID;
    }

    public void updateCustomer(Customer customer) {
        int i = 0;
        for (var c : customers) {
            if (c.getId() == customer.getId()) {
                customers.set(i, customer);
                return;
            }
            i++;
        }
        throw new IllegalArgumentException("Event with the ID = " + customer.getId() + " does not exist!");
    }

    public void deleteCustomer(long id) {
        for (var c : customers) {
            if (c.getId() == id) {
                customers.remove(c);
                idService.releaseId(id);
                return;
            }
        }
    }

    public void deleteAllCustomers() {
        customers.forEach(customer -> idService.releaseId(customer.getId()));
        customers = new ArrayList<>();
    }

    public Customer getCustomerById(long id) {
        for (var c : customers) {
            if (c.getId() == id)
                return c;
        }

        throw new IllegalArgumentException("Event with the ID = " + id + " does not exist!");
    }

    public Collection<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }

    private boolean isEmailAddressInvalid(String emailAddress) {
        int indexOfAtSign = emailAddress.indexOf('@');

        if (indexOfAtSign <= 0 || indexOfAtSign == emailAddress.length() - 1)
            return true;

        String domain = emailAddress.substring(indexOfAtSign + 1);

        String[] domainParts = domain.split("\\.");

        if (domainParts.length != 2)
            return true;

        if (domainParts[0].length() == 0)
            return true;

        if (domainParts[1].length() <= 1)
            return true;

        return false;
    }

    private ArrayList<Customer> customers;
    private IDService idService;

    private final static int MINIMUM_AGE = 18;
}