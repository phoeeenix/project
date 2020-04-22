package pl.myCompany.category;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

  Category findById(long id);

  @Transactional
  @Modifying
  @Query("update Category category set category.name = :newName where category.id = :id")
  void changeCategoryName(@Param("id") long id, @Param("newName") String newName);

}
