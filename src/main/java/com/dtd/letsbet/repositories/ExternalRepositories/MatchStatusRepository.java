package com.dtd.letsbet.repositories.ExternalRepositories;

import com.dtd.letsbet.model.ExternalData.MatchStatus;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Michal on 24.05.2017.
 */
public interface MatchStatusRepository extends CrudRepository<MatchStatus, Long> {
    void deleteAll();
}
