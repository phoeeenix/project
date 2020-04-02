package com.company.accounts;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountHelpers {

/*
  private Account account;
  @Autowired
  public AccountHelpers(Account account) {
    this.account = account;
  } //TODO Question: co gdybym robił w ten sposób? (doprowadzał pomocnicze accounty do testów z wstrzyknięciem konstruktora)
*/
  public static Account createTestAccount() { // za pomoca metody statycznej, która jest dostępna dla wszytskich klas nie musimy używać springa
    Account testAccount = new Account(1L, "testAccount", BigDecimal.valueOf(1000));
    return testAccount;
  }
}
