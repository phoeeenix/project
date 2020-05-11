package pl.myCompany.transaction;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

  private TranscationRepository transcationRepository;

  @Autowired
  public TransactionService(TranscationRepository transcationRepository) {
    this.transcationRepository = transcationRepository;
  }

  public Iterable<Transaction> getTransactions() {
    return transcationRepository.findAll();
  }

  public Optional<Transaction> getTransaction(long id) {
    return transcationRepository.findById(id);
  }

  public Transaction createTransaction(Transaction transactionFromController) {
    return transcationRepository.save(transactionFromController);
  }
}
