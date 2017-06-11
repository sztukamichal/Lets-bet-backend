package com.dtd.letsbet.repositories.ExternalRepositories;

import com.dtd.letsbet.model.ExternalData.Standing;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Michal on 24.05.2017.
 */
public interface StandingRepository extends CrudRepository<Standing, Long> {
    void deleteAll();
}
