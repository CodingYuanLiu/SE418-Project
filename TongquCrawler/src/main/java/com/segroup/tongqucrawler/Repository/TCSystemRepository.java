package com.segroup.tongqucrawler.Repository;

import com.segroup.tongqucrawler.Entity.TCSystem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface TCSystemRepository extends JpaRepository<TCSystem, String> {
    public TCSystem findByTcskey(String tcskey);
}
