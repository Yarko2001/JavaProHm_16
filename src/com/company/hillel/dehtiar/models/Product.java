package com.company.hillel.dehtiar.models;

import java.time.LocalDate;

public class Product {

  private final String type;
  private Integer price;
  private boolean discount;
  private final LocalDate addDate;

  public Product(String type, Integer price, boolean discount, LocalDate addDate) {
    this.type = type;
    this.price = price;
    this.discount = discount;
    this.addDate = addDate;
  }

  public String getType() {
    return type;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public LocalDate getAddDate() {
    return addDate;
  }

  public boolean isDiscount() {
    return discount;
  }

  public void setDiscount(boolean discount) {
    this.discount = discount;
  }

  @Override
  public String toString() {
    return "Product{" +
        "type='" + type + '\'' +
        ", price=" + price +
        ", discount=" + discount +
        ", addDate=" + addDate +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Product product = (Product) o;

    if (discount != product.discount) {
      return false;
    }
    if (type != null ? !type.equals(product.type) : product.type != null) {
      return false;
    }
    if (price != null ? !price.equals(product.price) : product.price != null) {
      return false;
    }
    return addDate != null ? addDate.equals(product.addDate) : product.addDate == null;
  }

  @Override
  public int hashCode() {
    int result = type != null ? type.hashCode() : 0;
    result = 31 * result + (price != null ? price.hashCode() : 0);
    result = 31 * result + (discount ? 1 : 0);
    result = 31 * result + (addDate != null ? addDate.hashCode() : 0);
    return result;
  }
}
