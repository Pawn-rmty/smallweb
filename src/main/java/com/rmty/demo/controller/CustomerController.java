package com.rmty.demo.controller;

import java.util.*;
import com.rmty.demo.model.Customer;
import com.rmty.demo.service.CustomerService;
import com.rmty.framework.annotation.Action;
import com.rmty.framework.annotation.Controller;
import com.rmty.framework.annotation.Inject;
import com.rmty.framework.bean.Param;
import com.rmty.framework.bean.View;



@Controller
public class CustomerController {

    @Inject
    private CustomerService customerService;


    @Action("get:/test")
    public View test(Param param){
        return new View("test.jsp");
    }

    @Action("get:/customer")
    public View index(Param param) {
        List<Customer> customerList = customerService.getCustomerList();
        return new View("customer.jsp").addModel("customerList", customerList);
    }

}