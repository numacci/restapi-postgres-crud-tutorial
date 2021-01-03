package com.numacci.api.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.numacci.api.dto.Customer;
import java.io.File;
import java.util.List;
import javax.sql.DataSource;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class CustomerMapperTest {

  @Autowired
  private CustomerMapper mapper;

  @Autowired
  private DataSource ds;

  private IDatabaseConnection dbconn;

  private IDataSet inputCsvDataSet;

  /**
   * Clean and insert test data before each test method.
   *
   * @throws Exception SQLException thrown when connecting to database
   */
  @BeforeEach
  public void setup() throws Exception {
    this.dbconn = new DatabaseConnection(this.ds.getConnection());
    this.inputCsvDataSet =
        new CsvDataSet(new File("src/test/resources/com/numacci/api/repository"));
    DatabaseOperation.CLEAN_INSERT.execute(dbconn, inputCsvDataSet);
  }

  /**
   * Close database connection after each test method.
   *
   * @throws Exception SQLException thrown when closing the connection
   */
  @AfterEach
  public void teardown() throws Exception {
    this.dbconn.close();
  }

  @DisplayName("INSERT TEST: Check if the data is inserted as expected.")
  @Test
  public void testInsert() {
    Customer customer = new Customer();
    customer.setId("100");
    customer.setUsername("user100");
    customer.setEmail("test.user.100@example.com");
    customer.setPhoneNumber("01234567890");
    customer.setPostCode("1234567");

    assertEquals(1, mapper.insert(customer));
  }

  @DisplayName("SELECT TEST: Check if all records retrieved.")
  @Test
  public void testSelectAll() {
    List<Customer> actuals = mapper.selectAll();
    assertEquals(10, actuals.size());
  }

  @DisplayName("SELECT TEST: Check if a record with the same id as provided retrieved.")
  @Test
  public void testSelect() {
    Customer actual = mapper.select("002");
    assertEquals("002", actual.getId());
    assertEquals("user002", actual.getUsername());
    assertEquals("test.user.002@example.com", actual.getEmail());
    assertEquals("23456789012", actual.getPhoneNumber());
    assertEquals("2345671", actual.getPostCode());
  }

  @DisplayName("UPDATE TEST: Check if the data is updated as expected.")
  @Test
  public void testUpdate() {
    Customer customer = new Customer();
    customer.setId("002");
    customer.setUsername("user002");
    customer.setEmail("test.user.002@example.com");
    customer.setPhoneNumber("23456789012");
    customer.setPostCode("2345671");

    assertEquals(1, mapper.update(customer));
  }

  @DisplayName("DELETE TEST: Check if the data is deleted as expected.")
  @Test
  public void testDelete() {
    assertEquals(1, mapper.delete("002"));
  }
}
