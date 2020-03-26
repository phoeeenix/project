package com.company.accounts;

import java.math.BigDecimal;
import lombok.Builder;

@Builder
public class Account {

  Long id;
  String description;
  //Currency currency;
  BigDecimal sumOfMoney;

  public Account(Long id, String description, BigDecimal sumOfMoney) {
    this.id = id;
    this.description = description;
    this.sumOfMoney = sumOfMoney;
  }

  public Account() {}

  public Long getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public BigDecimal getSumOfMoney() {
    return sumOfMoney;
  }

  public void setID(Long id){
    this.id = id;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setSumOfMoney(BigDecimal sumOfMoney) {
    this.sumOfMoney = sumOfMoney;
  }
}
