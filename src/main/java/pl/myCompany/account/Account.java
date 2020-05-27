package pl.myCompany.account;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;

@Builder
@Entity
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String description;
  //Currency currency;
  private BigDecimal sumOfMoney;
  //TODO I dont like "sumOfMoney" name it maybe "balance"

  public Account(Long id, String description, BigDecimal sumOfMoney) {
    this.id = id;
    this.description = description;
    this.sumOfMoney = sumOfMoney;
  }

  public Account(String description, BigDecimal sumOfMoney) {
    this.description = description;
    this.sumOfMoney = sumOfMoney;
  }

  public Account() {
  }  //TODO Question: Zmienic na protected jka w tutorialu JPA?

  public Long getId() {
    return id;
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

  public void setID(Long id) {
    this.id = id;
  }

  public String toString() {
    return String.format("Account[id = %d, description = '%s', sumOfMoney = %d]", id, description, sumOfMoney.intValue());
  }

  //TODO You can use lombok to generate getters setters and toString()
}
