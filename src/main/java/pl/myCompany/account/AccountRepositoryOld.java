package pl.myCompany.account;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//@Repository
public class AccountRepositoryOld {
  //TODO please remove code which is not used

  private Map<Long, Account> mapForAccounts = new HashMap<>();
  private long id = 0;

  public long getId() {
    return id;
  }

  public long getAndIncreaseIdNumber() {
    long actualId = id;
    id = id + 1;
    return actualId;
  }

  public Collection<Account> getAccounts() {
    return mapForAccounts.values();
  }

  public Account getAccount(long id) {
    return mapForAccounts.get(id);
  }

  public long addAccount(Account accountToAdd) {
    accountToAdd.setID(getAndIncreaseIdNumber());
    mapForAccounts.put(accountToAdd.getId(), accountToAdd);
    return accountToAdd.getId();
  }

  /*public long addAccountRequest(AccountRequest accountRequestToBeAdded) {
    long idForAccountRequestToAdd = getAndIncreaseIdNumber();
    mapForAccounts.put(idForAccountRequestToAdd, convertAccountRequestToAccount(accountRequestToBeAdded));
    return idForAccountRequestToAdd;
  }*/

  public Account changeAccountBalance(long id, BigDecimal newBalance) {
    mapForAccounts.get(id).setSumOfMoney(newBalance);
    return mapForAccounts.get(id);
  }

  public Account changeDescriptionOfAccount(long id, String description) {
    mapForAccounts.get(id).setDescription(description);
    return mapForAccounts.get(id);
  }

  public Account changeAccount(long id, Account accountForChange) {
    return mapForAccounts.put(id, accountForChange);
  }

  public Account deleteAccount(long id) {
    mapForAccounts.remove(id);
    String returnStatement = "Account id " + id + " has been deleted.";
    return mapForAccounts.get(id);
  }

}
