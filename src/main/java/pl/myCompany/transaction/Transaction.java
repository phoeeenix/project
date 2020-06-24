package pl.myCompany.transaction;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

/*  public Transaction() {
  }

  public Transaction(Long id, String description, Long categoryId, LocalDate localDate, boolean isPlanned) {
    this.id = id;
    this.description = description;
    this.categoryId = categoryId;
    this.localDate = localDate;
    this.isPlanned = isPlanned;
  }*/

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String description;

  private Long categoryId;

  private LocalDate localDate;

  //private List<AccountPriceEntry> accountPriceEntryList;

  private boolean isPlanned;

}
