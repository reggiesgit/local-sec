package com.inter.challenge.controller;

import com.inter.challenge.component.CacheComponent;
import com.inter.challenge.component.RSAComponent;
import com.inter.challenge.exception.NotFoundException;
import com.inter.challenge.model.User;
import com.inter.challenge.model.VerifierDigit;
import com.inter.challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for challenge.
 * 14/07/2021
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    private CacheComponent cacheComponent = new CacheComponent();
    private RSAComponent rsaComponent = new RSAComponent();

    @PostMapping("/")
    public ResponseEntity<User> create(@RequestBody User user) {
        User created = repository.save(user);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable(value = "id") Long id) throws NotFoundException {
        User found = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No user found with ID: " + id));
        return ResponseEntity.ok(found);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateById(@PathVariable(value = "id") Long id, @RequestBody User user) throws NotFoundException {
        User original = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No user found with ID: " + id));
        original.setName(user.getName());
        original.setEmail(user.getEmail());
        User updated = repository.save(original);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable(value = "id") Long id) throws NotFoundException {
        User original = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No user found with ID: " + id));
        repository.deleteById(original.getId());
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}/verifiers")
    public ResponseEntity<Set<VerifierDigit>> findUserVerifiers(@PathVariable(value = "id") Long id) throws NotFoundException {
        User found = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No user found with ID: " + id));
        Set<VerifierDigit> digits = new HashSet<>();
        digits.addAll(found.getVerifiers());
        return ResponseEntity.ok(digits);
    }

    @PutMapping("/{id}/add")
    public ResponseEntity<User> addVerifiers(@PathVariable(value = "id") Long id, @RequestParam String code) throws NotFoundException {
        User original = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No user found with ID: " + id));
        VerifierDigit pair = cacheComponent.resolveAndCacheVerifier(code);
        original.getVerifiers().add(pair);
        User saved = repository.save(original);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}/encrypt")
    public ResponseEntity<User> encryptUserInfo(@PathVariable(value = "id") Long id, @RequestBody String stringKey) throws Exception {
        User original = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No user found with ID: " + id));

        original.setName(rsaComponent.encrypt(original.getName(), stringKey));
        original.setEmail(rsaComponent.encrypt(original.getEmail(), stringKey));
        User saved = repository.save(original);
        return ResponseEntity.ok(saved);
    }
}
