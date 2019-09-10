package pl.rentedi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.rentedi.domains.sec.User;
import pl.rentedi.repository.UsersRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    public List<User> findAll(){
        return usersRepository.findAll();
    }
}
