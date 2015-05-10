package com.evo.backend.repos;

import com.evo.backend.entities.Push;
import com.evo.backend.entities.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by prashanth.a on 10/05/15.
 */
public interface UsersRepository extends MongoRepository<Users, String> {
    public Users findByDeviceId(String deviceId);
}
