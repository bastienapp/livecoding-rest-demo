package com.example.restDemo.repository;

import com.example.restDemo.entity.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {

    List<Computer> findAllByNameContains(String name);

    @Query("SELECT c FROM Computer c WHERE c.price < :price")
    List<Computer> findAllCheapComputers(@Param("price") Double price);
}
