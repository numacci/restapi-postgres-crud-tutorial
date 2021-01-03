package com.numacci.api.controller;

import com.numacci.api.dto.Customer;
import com.numacci.api.dto.CustomerResponse;
import com.numacci.api.service.CustomerService;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

  private CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @PostMapping
  public CustomerResponse post(@Validated @RequestBody Customer customer, Errors errors) {
    CustomerResponse res = new CustomerResponse();
    // if the fields of requested customer object are invalid,
    // return only error messages.
    if (errors.hasErrors()) {
      res.setMessage(errors.getAllErrors()
          .stream()
          .map(DefaultMessageSourceResolvable::getDefaultMessage)
          .collect(Collectors.joining(",")));
      return res;
    }
    // NOTE: You can also validate whether insertion succeeded or not here.
    Customer inserted = customerService.insert(customer);
    res.setCustomer(inserted);
    return res;
  }
}
