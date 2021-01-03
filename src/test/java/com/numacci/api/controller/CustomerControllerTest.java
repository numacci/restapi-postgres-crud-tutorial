package com.numacci.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.numacci.api.dto.Customer;
import com.numacci.api.service.CustomerService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.Errors;

public class CustomerControllerTest {

  @Mock
  private CustomerService customerService;

  @InjectMocks
  private CustomerController controller;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @DisplayName("CREATE TEST: Check if a new customer is registered and returned.")
  @Test
  public void testPost() {
    Customer customer = new Customer();
    customer.setId("100");
    customer.setUsername("user100");
    customer.setEmail("test.user.100@example.com");
    customer.setPhoneNumber("01234567890");
    customer.setPostCode("1234567");

    when(customerService.register(customer)).thenReturn(customer);

    Errors errors = mock(Errors.class);
    Customer response = controller.post(customer, errors);

    assertEquals(customer, response);
  }

  @DisplayName("READ TEST: Check if all customer information retrieved.")
  @Test
  public void testGetAll() {
    Customer customer2 = new Customer();
    customer2.setId("002");
    customer2.setUsername("user002");
    customer2.setEmail("test.user.002@example.com");
    customer2.setPhoneNumber("23456789012");
    customer2.setPostCode("2345671");
    Customer customer3 = new Customer();
    customer3.setId("003");
    customer3.setUsername("user003");
    customer3.setEmail("test.user.003@example.com");
    customer3.setPhoneNumber("34567890123");
    customer3.setPostCode("3456712");
    Customer customer4 = new Customer();
    customer4.setId("004");
    customer4.setUsername("user004");
    customer4.setEmail("test.user.004@example.com");
    customer4.setPhoneNumber("45678901234");
    customer4.setPostCode("4567123");

    List<Customer> customers = new ArrayList<>();
    customers.add(customer2);
    customers.add(customer3);
    customers.add(customer4);
    when(customerService.retrieve()).thenReturn(customers);

    List<Customer> actuals = controller.get();
    assertEquals(3, actuals.size());
    verify(customerService, times(1)).retrieve();
  }

  @DisplayName("READ TEST: Check if expected customer information retrieved.")
  @Test
  public void testGet() {
    Customer customer = new Customer();
    customer.setId("002");
    customer.setUsername("user002");
    customer.setEmail("test.user.002@example.com");
    customer.setPhoneNumber("23456789012");
    customer.setPostCode("2345671");

    when(customerService.retrieve(customer.getId())).thenReturn(customer);
    Customer actual = controller.get("002");
    assertEquals(customer, actual);
    verify(customerService, times(1)).retrieve(customer.getId());
  }

  @DisplayName("UPDATE TEST: Check if update succeeded.")
  @Test
  public void testPatch() {
    Customer customer = new Customer();
    customer.setId("002");
    customer.setUsername("user002");
    customer.setEmail("test.user.002@example.com");
    customer.setPhoneNumber("23456789012");
    customer.setPostCode("2345671");

    when(customerService.update(customer)).thenReturn(customer);

    Errors errors = mock(Errors.class);
    Customer response = controller.patch(customer, errors);

    assertEquals(customer, response);
  }

  @DisplayName("DELETE TEST: Check if deletion succeeded.")
  @Test
  public void testDelete() {
    String id = "002";
    when(customerService.delete(id)).thenReturn(id);
    String actual = controller.delete(id);
    assertEquals(id, actual);
    verify(customerService, times(1)).delete(id);
  }

  @DisplayName("CREATE TEST: Check if controller throws exception when some fields are invalid.")
  @Test
  public void testPostAbnormal() {
    Customer customer = new Customer();
    customer.setId("100");
    customer.setPhoneNumber("01234567890");
    customer.setPostCode("1234567");

    // Call post method with empty username and email.
    Errors errors = mock(Errors.class);
    when(errors.hasErrors()).thenReturn(true);
    assertThrows(RuntimeException.class, () -> controller.post(customer, errors));
  }

  @DisplayName("UPDATE TEST: Check if controller throws exception when some fields are invalid.")
  @Test
  public void testPatchAbnormal() {
    Customer customer = new Customer();
    customer.setId("002");
    customer.setPhoneNumber("23456789012");
    customer.setPostCode("2345671");

    // Call patch method with empty username and email.
    Errors errors = mock(Errors.class);
    when(errors.hasErrors()).thenReturn(true);
    assertThrows(RuntimeException.class, () -> controller.patch(customer, errors));
  }
}
