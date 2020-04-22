package pl.myCompany.account;

import java.math.BigDecimal;

public class AccountRequest {

  String description;
  BigDecimal sumOfMoney;

  public AccountRequest() {  //MUSI BYĆ PUSTY KONSUTRKOT ŻEBY JACKSON STWORZYŁ OBIEKT!!!
  }

  public AccountRequest(String description, BigDecimal sumOfMoney) {
    this.description = description;
    this.sumOfMoney = sumOfMoney;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getSumOfMoney() {
    return sumOfMoney;
  }

  public void setSumOfMoney(BigDecimal sumOfMoney) {
    this.sumOfMoney = sumOfMoney;
  }

}
