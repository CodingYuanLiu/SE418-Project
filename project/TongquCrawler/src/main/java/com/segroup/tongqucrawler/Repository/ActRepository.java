package com.segroup.tongqucrawler.Repository;

import com.segroup.tongqucrawler.Entity.Act;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActRepository extends JpaRepository<Act, Integer> {
    public Act findByActid(Integer actid);
}
