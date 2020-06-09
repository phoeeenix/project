package pl.myCompany.category;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CategoryController {

  private CategoryService categoryService;
  private List<Long> parentCategoryIds = new ArrayList<>();

  @Autowired
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  private List<Long> categories = new ArrayList<>();

  @GetMapping("/categories")
  public ResponseEntity<List<Category>> getCategories() {
    return ResponseEntity.ok(categoryService.getCategories());
  }

  @GetMapping("/category/{id}")
  public ResponseEntity<Optional<Category>> getCategoryById(@PathVariable long id) {
    ResponseEntity<Optional<Category>> response = checkIfCategoryExists(id);
    if (response.equals(ResponseEntity.notFound().build())) {
      return response;
    }

    return ResponseEntity.ok(categoryService.getCategoryById(id));
  }

  @PostMapping("/category")  //TODO QUESTION: Sometimes response is 200, sometimes error, why? !!!!!!!!!!!!!
  public ResponseEntity<Category> createCategory(@RequestBody CategoryRequest categoryRequest) {
    Category categoryForService = convertCategoryRequestToCategory(categoryRequest);
    return ResponseEntity.ok(categoryService.createCategory(categoryForService));
  }

  @DeleteMapping("/category/{id}")
  public ResponseEntity<?> deleteCategory(@PathVariable long id) {
    ResponseEntity<Optional<Category>> response = checkIfCategoryExists(id);
    if (response.equals(ResponseEntity.notFound().build()))
      return response;
    categoryService.deleteCategory(id);
    return ResponseEntity.ok().build();

  }

  @PutMapping("/changeCategoryName/{id}")
  public ResponseEntity<?> changeCategoryName(@PathVariable long id, @RequestBody String newName) {
    ResponseEntity<Optional<Category>> response = checkIfCategoryExists(id);
    if (response.equals(ResponseEntity.notFound().build()))
      return response;
    categoryService.changeCategoryName(id, newName);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/category/{id}")
  public ResponseEntity<?> changeCategory(@PathVariable long id, @RequestBody CategoryRequest categoryRequest) {
    ResponseEntity<Optional<Category>> response = checkIfCategoryExists(id);
    if (response.equals(ResponseEntity.notFound().build())) {
      return response;
    }
    Category categoryForService = convertCategoryRequestToCategory(categoryRequest);
    categoryService.changeCategory(id, categoryForService);
    return ResponseEntity.ok().build();
  }

  public Category convertCategoryRequestToCategory(CategoryRequest categoryRequest) {

    Long parentCategoryId = categoryRequest.getParentCategoryId();
    categories = getParentCategoryIds();

    if (parentCategoryId != null && categories.contains(parentCategoryId)) {
      System.out.println("The given Id already exists in database");
      return null;
    } else {
      return Category.builder().id(null)
          .name(categoryRequest.getName())
          .parentCategory(parentCategoryId == null ? null : Category.builder().id(parentCategoryId).build())
          .build();
    }
  }

  public List<Long> getParentCategoryIds() {
    //List<Long> categories = new ArrayList<>();
    //categories.clear();
    Iterable<Category> categories2 = categoryService.getCategories();

    for (Category category : categories2) {
      categories.add(category.getId());
    }
    return categories;
  }

  public ResponseEntity<Optional<Category>> checkIfCategoryExists(long id) {
    Optional<Category> category = categoryService.getCategoryById(id);
    if (category.isEmpty()) {
      log.info("Category with id = {} was not found", id);
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().build();
  }

}
