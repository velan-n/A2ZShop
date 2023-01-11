package com.a2zshop.microservices.productinfoservice.service;

import com.a2zshop.microservices.productinfoservice.exception.CategoryNotFoundException;
import com.a2zshop.microservices.productinfoservice.exception.DuplicateEntryException;
import com.a2zshop.microservices.productinfoservice.exception.SubCategoryNotFoundException;
import com.a2zshop.microservices.productinfoservice.model.Category;
import com.a2zshop.microservices.productinfoservice.model.SubCategory;

import java.util.List;

public interface CategoryService {
   Category addCategory(Category category) throws DuplicateEntryException;

   SubCategory addSubCategory(long categoryId, SubCategory subCategory) throws CategoryNotFoundException, DuplicateEntryException;

   boolean doesCategoryExists(Category category);

   Category retrieveCategoryById(long categoryId) throws CategoryNotFoundException;

   List<Category> retrieveAllCategories();

   List<SubCategory> retrieveAllSubCategoriesForCategory(long categoryId) throws CategoryNotFoundException, SubCategoryNotFoundException;

//   SubCategory retrieveSubCategoryForCategory(long categoryId, long subCategoryId) throws CategoryNotFoundException, SubCategoryNotFoundException;

//    public Category addCategory(Category category);

//    public Category findCategoryByName(String categoryName) throws CategoryNotfoundException;

}
