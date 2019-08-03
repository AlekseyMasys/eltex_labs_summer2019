package ru.masis.repositoryes;

import org.springframework.data.repository.CrudRepository;
import ru.masis.entities.User;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByFname(String fname);
    List<User> findByFnameAndLname(String fname, String lname);
}
