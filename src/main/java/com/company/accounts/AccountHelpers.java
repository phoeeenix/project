package com.company.accounts;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountHelpers {

  private Account account;
  @Autowired
  public AccountHelpers(Account account) {
    this.account = account;
  }

  AccountHelpers testAccount = new AccountHelpers(new Account(1L, "testAccount", BigDecimal.valueOf(1000)));
}
