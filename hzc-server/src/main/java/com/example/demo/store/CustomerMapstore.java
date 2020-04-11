package com.example.demo.store;

import com.example.demo.pojo.Customer;
import com.example.demo.repository.CustomerRepository;
import com.hazelcast.core.MapStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class CustomerMapstore implements MapStore<Integer, Customer> {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void store(Integer integer, Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void storeAll(Map<Integer, Customer> map) {
        customerRepository.saveAll(map.values());
    }

    @Override
    public void delete(Integer integer) {
        customerRepository.delete(load(integer));

    }

    @Override
    public void deleteAll(Collection<Integer> collection) {
        customerRepository.deleteAll(customerRepository.findAllById(collection));
    }

    @Override
    public Customer load(Integer item) {
        System.out.println("<=-------------------->"+item);
        Optional<Customer> item1 = customerRepository.findById(item);
        if (item1.isPresent())
            return item1.get();
        return null;
    }

    @Override
    public Map<Integer, Customer> loadAll(Collection<Integer> collection) {
        Iterable<Customer> customerIterator = customerRepository.findAll();
        Map<Integer, Customer> integerCustomerMap = new HashMap<>();
        customerIterator.forEach(item -> {
            integerCustomerMap.put(item.getId(),item);
        });
        return integerCustomerMap;
    }

    @Override
    public Iterable<Integer> loadAllKeys() {
        Iterable<Customer> customerIterator = customerRepository.findAll();
        return StreamSupport.stream(customerIterator.spliterator(),false)
                .map(Customer::getId)
                .collect(Collectors.toList());
    }
}
