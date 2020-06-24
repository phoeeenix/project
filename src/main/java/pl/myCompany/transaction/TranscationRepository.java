package pl.myCompany.transaction;

import org.springframework.data.repository.CrudRepository;

public interface TranscationRepository extends CrudRepository<Transaction, Long> {

}
