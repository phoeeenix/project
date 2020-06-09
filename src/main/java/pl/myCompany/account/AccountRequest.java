package pl.myCompany.account;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequest {

  String description;
  BigDecimal sumOfMoney;

  public AccountRequest() {  //MUSI BYĆ PUSTY KONSUTRKOT ŻEBY JACKSON STWORZYŁ OBIEKT!!!
  }

  public AccountRequest(String description, BigDecimal sumOfMoney) {
    this.description = description;
    this.sumOfMoney = sumOfMoney;
  }

}
