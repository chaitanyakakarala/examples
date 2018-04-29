package com.example.demo.repo;

import com.example.demo.pojo.SalesInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepo extends JpaRepository<SalesInfo,Integer> {



}
