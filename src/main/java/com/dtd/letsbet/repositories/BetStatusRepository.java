package com.dtd.letsbet.repositories;

import com.dtd.letsbet.model.BetStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Michal on 24.05.2017.
 */
public interface BetStatusRepository extends CrudRepository<BetStatus, Long> {
    List<BetStatus> findByName(String name);
}
