package com.numacci.api.service;

import com.numacci.api.dto.Customer;
import java.util.List;

public interface CustomerService {

  /**
   * Register a new customer information.
   *
   * @param customer customer object which we want to register
   * @return customer object registered
   */
  Customer register(Customer customer);

  /**
   * Retrieve all customer information.
   *
   * @return all customer information
   */
  List<Customer> retrieve();

  /**
   * Retrieve the customer information having the same id as provided.
   *
   * @param id identity of customer information
   * @return customer information having the same id as provided
   */
  Customer retrieve(String id);

  /**
   * Update customer information.
   *
   * @param customer customer object which we want to update
   * @return customer object updated
   */
  Customer update(Customer customer);

  /**
   * Delete customer information.
   *
   * @param id identity of customer information
   * @return identity of deleted customer
   */
  String delete(String id);
}
