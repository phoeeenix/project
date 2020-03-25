package com.company.accounts;

import java.math.BigDecimal;

@Buil
public class AccountRequest {

  String description;
  BigDecimal sumOfMoney;

  public String getDescription() {
    return description;
  }

  public BigDecimal getSumOfMoney() {
    return sumOfMoney;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setSumOfMoney(BigDecimal sumOfMoney) {
    this.sumOfMoney = sumOfMoney;
  }

  public AccountRequest(String description, BigDecimal sumOfMoney) {
    this.description = description;
    this.sumOfMoney = sumOfMoney;
  }

}
