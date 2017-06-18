package com.dtd.letsbet.repositories;

import com.dtd.letsbet.model.AccountType;
import org.springframework.data.repository.CrudRepository;

public interface AccountTypeRepository extends CrudRepository<AccountType, Long> {
    void deleteAll();
}