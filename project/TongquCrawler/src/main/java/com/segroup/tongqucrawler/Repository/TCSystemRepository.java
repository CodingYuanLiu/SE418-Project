package com.segroup.tongqucrawler.Repository;

import com.segroup.tongqucrawler.Entity.TCSystem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TCSystemRepository extends MongoRepository<TCSystem, String> {
    public TCSystem findByTcskey(String tcskey);
    public void deleteByTcskey(String tcskey);
}
