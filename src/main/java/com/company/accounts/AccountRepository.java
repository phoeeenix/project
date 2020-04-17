package com.company.accounts;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

  List<Account> findAll();
  Account findById(long id); //TODO Question: Optional<T> findById(ID id)?
  //TODO Error Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'accountService' defined in file [C:\Users\pawel.beres\Documents\project\out\production\classes\com\company\accounts\AccountService.class]: Unsatisfied dependency expressed through constructor parameter 0; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'accountRepositoryJPA': Invocation of init method failed; nested exception is java.lang.IllegalArgumentException: Failed to create query for method public abstract java.util.List com.company.accounts.AccountRepository.findAllById(java.util.List)! Operator SIMPLE_PROPERTY on id requires a scalar argument, found interface java.util.List in method public abstract java.util.List com.company.accounts.AccountRepository.findAllById(java.util.List).
  List<Account> findByDescription(String description);
  List<Account> findAllById(List<Account> listOfIds); //TODO Question: w parametrze ma być lista z id'kami?
  boolean existsById(long id);
  void delete(Account account);

  @Modifying
  @Query("update Account account set account.sumOfMoney = :newBalance where account.id = :id")
  void updateAccountBalance(@Param("newBalance") BigDecimal newBalance, @Param("id") long id);

  @Modifying
  @Query("update Account account set account.descirption = :newBalance where account.id = :id")
  void updateAccountDescription(@Param("newDescription") String description, @Param("id") long id);
}

/*
  @Query("select account from Account account where lower(account.name) like lower(:nameToFind) AND account.userId = :id")
  List<Account> findByNameIgnoreCaseAndUserId(@Param("nameToFind") String nameToFind, @Param("id") long id);

  Optional<Account> findByIdAndUserId(long id, long userId);

  @Modifying(clearAutomatically = true)
  @Query("update Account account set account.balance = :newBalance where account.id = :id")
  void updateAccountBalance(@Param("newBalance") BigDecimal newBalance, @Param("id") long accountId);*/
