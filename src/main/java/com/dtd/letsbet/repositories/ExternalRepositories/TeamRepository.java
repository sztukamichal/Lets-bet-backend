package com.dtd.letsbet.repositories.ExternalRepositories;

import com.dtd.letsbet.model.ExternalData.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Michal on 24.05.2017.
 */
public interface TeamRepository extends CrudRepository<Team, Long> {
    List<Team> findByName(String name);
    void deleteAll();
}
