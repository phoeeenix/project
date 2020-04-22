package pl.myCompany.account;

import java.math.BigDecimal;

public class AccountTestProvider {

  public static Account testPKOBankAccountWithId1() { // za pomoca metody statycznej, która jest dostępna dla wszytskich klas nie musimy używać springa
    return new Account(1L, "PKO bank", BigDecimal.valueOf(14L));
  }

  public static Account testMilleniumAccountWithId2() {
    return new Account(2L, "Millenium bank", BigDecimal.valueOf(21L));
  }

  public static Account testMbankAccountWithId3() {
    return new Account(3L, "mBank", BigDecimal.valueOf(100));
  }

  public static Account testSantanderBankAccountWithId4() {
    return new Account(4L, "Santander bank", BigDecimal.valueOf(200));
  }

}
