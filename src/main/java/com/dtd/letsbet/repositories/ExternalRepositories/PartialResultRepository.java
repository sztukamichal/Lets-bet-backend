package com.dtd.letsbet.repositories.ExternalRepositories;

import com.dtd.letsbet.model.ExternalData.PartialResult;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Michal on 24.05.2017.
 */
public interface PartialResultRepository extends CrudRepository<PartialResult, Long> {
    void deleteAll();
}
