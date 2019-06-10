package com.segroup.tongqucrawler.Repository;

import com.segroup.tongqucrawler.Entity.Act;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActRepository extends MongoRepository<Act, Integer> {
    public Act findByActid(Integer actid);
    public void deleteByActid(Integer actid);
}
