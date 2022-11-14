package com.company.hillel.dehtiar.exception;

public class CategoryNotFoundException extends Exception {

  public CategoryNotFoundException(String type) {
    super("There is no such type of product : (" + type + " )");
  }
}
