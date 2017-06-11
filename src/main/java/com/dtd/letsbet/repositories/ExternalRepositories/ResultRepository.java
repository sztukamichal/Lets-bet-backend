package com.dtd.letsbet.repositories.ExternalRepositories;

import com.dtd.letsbet.model.ExternalData.Result;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Michal on 24.05.2017.
 */
public interface ResultRepository extends CrudRepository<Result, Long> {
    void deleteAll();
}
