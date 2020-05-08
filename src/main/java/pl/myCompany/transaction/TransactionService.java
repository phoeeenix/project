package pl.myCompany.transaction;

import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

  private TranscationRepository transcationRepository;

  public Iterable<Transaction> getTransactions() {
    return transcationRepository.findAll();
  }

  public Optional<Transaction> getTransaction(long id) {
    return transcationRepository.findById(id);
  }
}
