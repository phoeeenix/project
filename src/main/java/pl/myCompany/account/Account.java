package pl.myCompany.account;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String description;
  //Currency currency;
  private BigDecimal balance;

 /* public Account(Long id, String description, BigDecimal balance) {
    this.id = id;
    this.description = description;
    this.balance = balance;
  }*/

  public Account(String description, BigDecimal balance) {
    this.description = description;
    this.balance = balance;
  }

/*  public Account() {
  }  //TODO Question: Zmienic na protected jka w tutorialu JPA?*/


}
