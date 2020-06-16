package com.example.restDemo.controller;

import com.example.restDemo.entity.Computer;
import com.example.restDemo.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MainController {

    @Autowired
    private ComputerRepository computerRepository;

    // lire tous les ordinateurs de la base
    @GetMapping("/computers")
    public List<Computer> findAll(@RequestParam(defaultValue = "ASC", required = false) String sort) {

        List<Computer> computers = computerRepository.findAll();
        if (!sort.equals("ASC")) {
            // trier les ordinateurs par ordre decroissant d'identifiant
            computers.sort((c1, c2) -> (int) (c2.getId() - c1.getId()));
        }
        return computers;
    }

    // lire un ordinateur par son id
    @GetMapping("/computers/{id}")
    public Computer findOne(@PathVariable Long id) {

        Optional<Computer> optionalComputer = computerRepository.findById(id);
        if (optionalComputer.isPresent()) {
            return optionalComputer.get();
        }
        // TODO : return 404
        return null;
    }

    // créer un ordinateur
    @PostMapping("/computers")
    public Computer save(@RequestBody Computer computer) {

        return computerRepository.save(computer);
    }

    // modifier un ordinateur
    @PutMapping("/computers")
    public Computer update(@RequestBody Computer computer) {

        return computerRepository.save(computer);
    }

    // supprimer un ordinateur par son id
    @DeleteMapping("/computers/{id}")
    public Boolean delete(@PathVariable Long id) {

        computerRepository.deleteById(id);

        return !computerRepository.findById(id).isPresent();
    }

    @GetMapping("/computers/name/{name}")
    public List<Computer> findAllByName(@PathVariable String name) {

        return computerRepository.findAllByNameContains(name);
    }

    @GetMapping("/computers/price/below/{price}")
    public List<Computer> findAllByPirceBelow(@PathVariable Double price) {

        return computerRepository.findAllCheapComputers(price);
    }

    @GetMapping("/")
    public String hello() {

        return "Hello World!";
    }
}
