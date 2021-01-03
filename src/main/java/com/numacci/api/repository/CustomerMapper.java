package com.numacci.api.repository;

import com.numacci.api.dto.Customer;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper {

  /**
   * Insert a new customer record to the database.
   *
   * @param customer customer object which we want to insert
   * @return the number of inserted records
   */
  int insert(Customer customer);

  /**
   * Select all records from database.
   *
   * @return all records stored in database
   */
  List<Customer> selectAll();

  /**
   * Retrieve a record with the same id as provided.
   *
   * @param id identity of customer record
   * @return customer record which has the same id as provided
   */
  Customer select(String id);

  /**
   * Update an existing record.
   *
   * @param customer customer object having the updating contents
   * @return the number of updated records
   */
  int update(Customer customer);

  /**
   * Delete a record with the same id as provided.
   *
   * @param id identity of customer record
   * @return the number of deleted records
   */
  int delete(String id);
}
