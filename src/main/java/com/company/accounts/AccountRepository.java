package com.company.accounts;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository {

  private Map<Long, Account> mapForAccounts = new HashMap<>();

  public long getId() {
    return id;
  }

  private long id = 0;

  public long getAndIncreaseIdNumber() {
    long actualId = id;
    id = id + 1;
    return actualId;
  }

  public long addAccount(Account accountToAdd) {
    accountToAdd.setID(getAndIncreaseIdNumber());
    mapForAccounts.put(accountToAdd.getId(), accountToAdd);
    return accountToAdd.getId();
  }

  public Account getAccount(long id) {
    return mapForAccounts.get(id);
  }

  public Collection<Account> getAccounts() {
    return mapForAccounts.values();
  }

  public Account changeAccountBalance(long id, BigDecimal balanceToChange) {
    mapForAccounts.get(id).setSumOfMoney(balanceToChange);
    return mapForAccounts.get(id);
  }

  public Account changeDescriptionOfAccount(long id, String description) {
    mapForAccounts.get(id).setDescription(description);
    return mapForAccounts.get(id);
  }

    public Account deleteAccount(long id){
      mapForAccounts.remove(id);
      String returnStatement = "Account id " + id + " has been deleted.";
      return mapForAccounts.get(id);
    }
  }
