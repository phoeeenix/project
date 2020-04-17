package com.company.accounts;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

  //private AccountRepositoryOld accountRepositoryOld;
  @Autowired
  private AccountRepository accountRepository;

  /*@Autowired
  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }*/

  public Iterable<Account> getAccounts() {
    return accountRepository.findAll();
  }

  public Account getAccount(long id) {
    return accountRepository.findById(id);
  }

  public Account addAccount(Account accountToAdd) {
    return accountRepository.save(accountToAdd);
  }

  public void changeAccountBalance(long id, BigDecimal newBalance) {
    accountRepository.updateAccountBalance(newBalance, id);
  }


  public void changeDescriptionOfAccount(long id, String description) {
    accountRepository.updateAccountDescription(description, id);
  }

  public void changeAccount(long id, Account newAccount) {
    //Account accountFromDB = getAccount(id);
    //accountRepository.updateAccount(id, newAccount);
   changeAccountBalance(id, newAccount.getSumOfMoney());
   changeDescriptionOfAccount(id, newAccount.getDescription());
  }

  public void deleteAccount(long id) {
    accountRepository.deleteById(id);
  }

}