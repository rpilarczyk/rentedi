package pl.rentedi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rentedi.domains.sec.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

    default User findByUsername(final String name) {
        User user = new User();
        return user;
    }
}
