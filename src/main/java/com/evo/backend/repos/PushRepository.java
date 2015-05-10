package com.evo.backend.repos;

import com.evo.backend.entities.Push;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by prashanth.a on 10/05/15.
 */
public interface PushRepository extends MongoRepository<Push, String>{
}


