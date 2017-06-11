package com.dtd.letsbet.repositories.ExternalRepositories;

import com.dtd.letsbet.model.ExternalData.Footballer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Michal on 24.05.2017.
 */
public interface FootballerRepository extends CrudRepository<Footballer, Long> {
    List<Footballer> findByName(String name);
    Footballer findOneByName(String name);
    void deleteAll();
}
