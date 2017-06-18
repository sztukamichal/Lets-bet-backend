package com.dtd.letsbet.repositories;

import com.dtd.letsbet.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
    void deleteAll();
}
