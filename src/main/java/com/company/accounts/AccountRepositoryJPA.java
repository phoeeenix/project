package com.company.accounts;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepositoryJPA extends CrudRepository<Account, Long> {

  Account findById(long id);
  List<Account> findByDescription(String description);
}
