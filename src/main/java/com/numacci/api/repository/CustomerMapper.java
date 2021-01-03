package com.numacci.api.repository;

import com.numacci.api.dto.Customer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper {

  int insert(Customer customer);
}
