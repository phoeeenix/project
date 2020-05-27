package pl.myCompany.account;

import java.math.BigDecimal;

public class AccountHelpers {

  //TODO i guess this code is for testing so please move it in test directory

  /*
    private Account pl.myCompany.account;
    @Autowired
    public AccountHelpers(Account pl.myCompany.account) {
      this.pl.myCompany.account = pl.myCompany.account;
    } //TODO Question: co gdybym robił w ten sposób? (doprowadzał pomocnicze accounty do testów z wstrzyknięciem konstruktora)
  */
  public static Account createTestAccount() { // za pomoca metody statycznej, która jest dostępna dla wszytskich klas nie musimy używać springa
    Account testAccount = new Account(1L, "testAccount", BigDecimal.valueOf(1000));
    return testAccount;
  }
}
