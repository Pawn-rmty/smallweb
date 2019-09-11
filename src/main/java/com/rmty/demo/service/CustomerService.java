package com.rmty.demo.service;

import java.util.List;
import com.rmty.demo.model.Customer;
import com.rmty.framework.annotation.Service;
import com.rmty.framework.helper.DatabaseHelper;

@Service
public class CustomerService {

    public List<Customer> getCustomerList() {
        String sql = "SELECT * FROM customer";
        return DatabaseHelper.queryEntityList(Customer.class, sql);
    }



}
