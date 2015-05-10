package com.evo.backend.repos;

import com.evo.backend.entities.News;
import com.evo.backend.entities.Push;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by prashanth.a on 10/05/15.
 */
public interface NewsRepository extends MongoRepository<News, String> {
    public News findByTag(String tag);
}
