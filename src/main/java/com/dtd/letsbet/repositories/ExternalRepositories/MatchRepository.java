package com.dtd.letsbet.repositories.ExternalRepositories;

import com.dtd.letsbet.model.ExternalData.Match;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Michal on 24.05.2017.
 */
public interface MatchRepository extends CrudRepository<Match, Long> {
    void deleteAll();
}
