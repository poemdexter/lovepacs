package org.lovepacs.repositories;

import org.lovepacs.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

    User findByName(String name);
}
