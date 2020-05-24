package com.itgarden.restweb.service;

import com.itgarden.restweb.model.Customer;

import java.util.List;

public interface BaseService<T> {

    public List<T> displayByName(String name);
}
