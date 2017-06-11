package com.dtd.letsbet.repositories.ExternalRepositories;

import com.dtd.letsbet.model.ExternalData.Competition;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Michal on 24.05.2017.
 */
public interface CompetitionRepository extends CrudRepository<Competition, Long> {
    List<Competition> findByCaption(String name);
    Competition findOneByCaption(String name);
    void deleteAll();
}
