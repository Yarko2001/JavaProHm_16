package com.company.hillel.dehtiar.service;

import com.company.hillel.dehtiar.exception.CategoryNotFoundException;
import com.company.hillel.dehtiar.models.Product;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Main {

  private static final String BOOK = "Book";
  private static final String TOY = "Toy";

  public static void main(String[] args) throws CategoryNotFoundException {

    List<Product> listOfProducts = new ArrayList<>();

    listOfProducts.add(new Product(TOY, 1250, true,
        LocalDate.of(2017, Month.NOVEMBER, 30)));
    listOfProducts.add(new Product(TOY, 1500, true,
        LocalDate.of(2016, Month.NOVEMBER, 30)));
    listOfProducts.add(new Product(BOOK, 280, true,
        LocalDate.of(2012, Month.NOVEMBER, 30)));
    listOfProducts.add(new Product(TOY, 190, true,
        LocalDate.of(2011, Month.NOVEMBER, 30)));
    listOfProducts.add(new Product(BOOK, 65, true,
        LocalDate.of(2022, Month.NOVEMBER, 30)));
    listOfProducts.add(new Product(BOOK, 45, true,
        LocalDate.of(2022, Month.NOVEMBER, 30)));

    streamTask1(listOfProducts);
    System.out.println();

    List<Product> discountedProductList = streamTask2(listOfProducts, 10);
    discountedProductList.forEach(System.out::println);
    System.out.println();

    try {
      streamTask3(listOfProducts, "Book");
    } catch (CategoryNotFoundException e) {
      e.printStackTrace();
    }
    System.out.println();

    List<Product> lastThreeProducts = streamTask4(listOfProducts);
    lastThreeProducts.forEach(System.out::println);
    System.out.println();

    System.out.println("The total cost : " + streamTask5(listOfProducts));
    System.out.println();

    Map<String, List<Product>> listMap = streamTask6(listOfProducts);
    listMap.forEach((k, v) -> System.out.println(k + ":" + v));

  }

  /**
   * @param list {@code List<Product>}
   *             <p> if list contains {@code BOOK} and {@code Prise > 250}.
   * @return filtered and modified {@code List<Product>}.
   */
  private static void streamTask1(List<Product> list) {
    List<Product> collectList = list.stream()
        .filter(a -> a.getPrice() > 250)
        .filter(b -> b.getType().equals("Book"))
        .collect(Collectors.toList());
    collectList.forEach(System.out::println);
  }

  /**
   * @param productList {@code List<Product>}
   *                    <p> if this list contains {@code BOOK} and {@code isDiscount == true}, then
   *                    apply discount 10%.
   * @return filtered and modified {@code List<Product>}.
   */
  private static List<Product> streamTask2(List<Product> productList, double discount) {
    List<Product> filteredProductList = productList.stream()
        .filter(product -> product.getType().equals("Book")).filter(Product::isDiscount)
        .collect(Collectors.toList());
    filteredProductList.forEach(
        product -> product.setPrice((int) (product.getPrice() * (1 - discount / 100))));
    return filteredProductList;
  }

  /**
   * @param list {@code List<Product>,String type}
   * @return the cheapest {@code Product} with type {@code BOOK}.
   * @throws NoSuchElementException if it wasn't found such kind of type.
   */
  private static void streamTask3(List<Product> list, String type)
      throws CategoryNotFoundException {
    if (list.stream().noneMatch(a -> a.getType().equals(type))) {
      throw new CategoryNotFoundException(type);
    }
    List<Product> minElementOfList = Collections.singletonList(list.stream()
        .filter(p -> p.getType().equals(type))
        .min(Comparator.comparing(Product::getPrice))
        .orElseThrow(NoSuchElementException::new));
    System.out.println(minElementOfList);
  }

  /**
   * @param list {@code List<Product>}
   * @return three last elements were added to {@code List<Product>}.
   */
  private static List<Product> streamTask4(List<Product> list) {
    return list.stream()
        .skip(list.size() - 3)
        .collect(Collectors.toList());

  }

  /**
   * @param list {@code List<Product>}
   *             <p>if this list contains {@code BOOK},
   *             {@code Prise <= 75} and product was added in 2022.
   * @return filtered and modified {@code List<Product>}.
   */
  private static Integer streamTask5(List<Product> list) {
    List<Product> totalCost = list.stream()
        .filter(product -> product.getType().equals("Book"))
        .filter(a -> a.getPrice() <= 75)
        .filter(b -> b.getAddDate().getYear() == 2022)
        .collect(Collectors.toList());
    return totalCost.stream()
        .mapToInt(Product::getPrice).sum();
  }

  /**
   * @param products {@code List<Product>}
   * @return Map kay = productTyp, value = List<Product>.
   */
  private static Map<String, List<Product>> streamTask6(List<Product> products) {
    return products.stream()
        .distinct()
        .collect(Collectors.groupingBy(Product::getType));

  }
}
