package pl.myCompany.transaction;

import java.time.LocalDate;
import lombok.Data;

@Data
public class TransactionRequest {

  private Long id;

  private String description;

  private Long categoryId;

  private LocalDate localDate;

  //private List<AccountPriceEntry> accountPriceEntryList;

  private boolean isPlanned;

}
