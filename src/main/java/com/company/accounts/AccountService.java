package com.company.accounts;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  private AccountRepositoryOld accountRepositoryOld;
  private AccountRepositoryJPA accountRepositoryJPA;

  @Autowired
  public AccountService(AccountRepositoryJPA accountRepositoryJPA) {
    this.accountRepositoryJPA = accountRepositoryJPA;
  }

  public Collection<Account> getAccounts() {
    return accountRepositoryJPA.findAll();
  }

  public Account getAccount(long id) {
    return accountRepositoryJPA.findByIdIn(id);
  }

  public Account addAccount(Account accountToAdd) {
    return accountRepositoryJPA.save(accountToAdd);
  }

  public Account changeAccountBalance(long id, BigDecimal newBalance) {
    return accountRepositoryOld.changeAccountBalance(id, newBalance);
  }

  public Account changeDescriptionOfAccount(long id, String description) {
    return accountRepositoryOld.changeDescriptionOfAccount(id, description);
  }

  public Account changeAccount(long id, Account accountToBeChanged) {
    return accountRepositoryOld.changeAccount(id, accountToBeChanged);
  }

  public void deleteAccount(long id) {
    accountRepositoryJPA.deleteById(id);
  }

}
