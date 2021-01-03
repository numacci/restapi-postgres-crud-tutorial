package com.numacci.api.service.impl;

import com.numacci.api.dto.Customer;
import com.numacci.api.repository.CustomerMapper;
import com.numacci.api.service.CustomerService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

  private CustomerMapper mapper;

  public CustomerServiceImpl(CustomerMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  public Customer register(Customer customer) {
    String formattedEmail = formatEmail(customer.getEmail());
    customer.setEmail(formattedEmail);

    mapper.insert(customer);
    return customer;
  }

  @Override
  public List<Customer> retrieve() {
    return mapper.selectAll();
  }

  @Override
  public Customer retrieve(String id) {
    return mapper.select(id);
  }

  @Override
  public Customer update(Customer customer) {
    String formattedEmail = formatEmail(customer.getEmail());
    customer.setEmail(formattedEmail);

    mapper.update(customer);
    return customer;
  }

  @Override
  public String delete(String id) {
    mapper.delete(id);
    return id;
  }

  private String formatEmail(String email) {
    String[] separatedEmail = email.split("@");
    return separatedEmail[0] + "@" + separatedEmail[1].toLowerCase();
  }
}
