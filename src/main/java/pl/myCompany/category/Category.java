package pl.myCompany.category;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Entity
@Getter
@Setter
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  @ManyToOne
  private Category parentCategory;

  public Category() {
  }

  public Category(Long id, String name, Category parentCategory) {
    this.id = id;
    this.name = name;
    this.parentCategory = parentCategory;
  }

 /* public Category(Long id, String name) {
    this.id = id;
    this.name = name;
  }*/

}
