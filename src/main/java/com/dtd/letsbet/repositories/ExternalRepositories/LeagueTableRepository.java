package com.dtd.letsbet.repositories.ExternalRepositories;

import com.dtd.letsbet.model.ExternalData.LeagueTable;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Michal on 24.05.2017.
 */
public interface LeagueTableRepository extends CrudRepository<LeagueTable, Long> {
    void deleteAll();
}
