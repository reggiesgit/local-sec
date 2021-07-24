package com.inter.challenge.controller;

import com.inter.challenge.component.CacheComponent;
import com.inter.challenge.component.DigitComponent;
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
 * 15/07/2021
 */

@RestController
@RequestMapping("/digit")
public class DigitController {

    @Autowired
    private UserRepository repository;
    private DigitComponent component = new DigitComponent();
    private CacheComponent cacheComponent = new CacheComponent();

    @GetMapping("/discover")
    public ResponseEntity<Integer> discoverDigit(@RequestParam String code, @RequestParam Long user) throws NotFoundException {
        VerifierDigit pair = cacheComponent.resolveAndCacheVerifier(code);
        User dummy = new User();
        if (user > 0) {
            dummy = repository.findById(user)
                    .orElseThrow(() -> new NotFoundException("No user found with ID: " + user));
            dummy.getVerifiers().add(pair);
            repository.save(dummy);
        }
        return ResponseEntity.ok(pair.getDigit());
    }

    @GetMapping("/retrieve/{userId}")
    public ResponseEntity<Set<VerifierDigit>> retrieveUserVerifiers(@PathVariable(value = "userId") Long userId) throws NotFoundException {
        User found = repository.findById(userId)
                .orElseThrow(() -> new NotFoundException("No user found with ID: " + userId));
        Set<VerifierDigit> digits = new HashSet<>();
        digits.addAll(found.getVerifiers());
        return ResponseEntity.ok(digits);
    }
}
