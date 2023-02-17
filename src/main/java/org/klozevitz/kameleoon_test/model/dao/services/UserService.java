package org.klozevitz.kameleoon_test.model.dao.services;

import org.klozevitz.kameleoon_test.model.dao.daoDB.IDaoUser;
import org.klozevitz.kameleoon_test.model.entities.Quote;
import org.klozevitz.kameleoon_test.model.entities.User;
import org.klozevitz.kameleoon_test.model.dao.repositories.IRepoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IDaoUser {
    @Autowired
    IRepoUser userRepo;

    @Autowired
    BCryptPasswordEncoder encoder;


//needed for tests
    @Override
    public User findById(int id) {
        for(Quote quote: userRepo.findById((long) id).orElse(new User()).getQuotes())
            System.out.println(quote.getId());
        return userRepo.findById((long) id).orElse(new User());
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepo.findAll();
    }

    @Override
    public User save(User user) {
//would not save duplicate usernames
        if (userRepo.findByName(user.getName()).isPresent()) {
            return new User();
        }
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
}
