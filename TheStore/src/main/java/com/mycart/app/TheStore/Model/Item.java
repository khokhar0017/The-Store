package com.mycart.app.TheStore.Model;

public class Item {

  private String id;
  private String name;
  private Category category;
  private double price;
  private String brand;

  public enum Category {
    Category1,
    Category2,
    Category3,
    Category4
  }

  private Item(Builder builder) {
    this.id = builder.id;
    this.name = builder.name;
    this.category = builder.category;
    this.price = builder.price;
    this.brand = builder.brand;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Category getCategory() {
    return category;
  }

  public double getPrice() {
    return price;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  @Override
  public String toString() {
    return "Item [name="
        + name
        + ", category="
        + category
        + ", price="
        + price
        + ", brand="
        + brand
        + ", id="
        + id
        + "]";
  }

  public static class Builder {

    private String id;
    private String name;
    private Category category;
    private double price;
    private String brand;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder category(Category category) {
      this.category = category;
      return this;
    }

    public Builder price(double price) {
      this.price = price;
      return this;
    }

    public Builder brand(String brand) {
      this.brand = brand;
      return this;
    }

    public Item build() {
      Item item = new Item(this);
      validateItemObject(item);
      return item;
    }

    private void validateItemObject(Item item) {
      // Do some basic validations to check
      // if user object does not break any assumption of system
    }
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((brand == null) ? 0 : brand.hashCode());
    result = prime * result + ((category == null) ? 0 : category.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    long temp;
    temp = Double.doubleToLongBits(price);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Item other = (Item) obj;
    if (brand == null) {
      if (other.brand != null) return false;
    } else if (!brand.equals(other.brand)) return false;
    if (category == null) {
      if (other.category != null) return false;
    } else if (!category.equals(other.category)) return false;
    if (id == null) {
      if (other.id != null) return false;
    } else if (!id.equals(other.id)) return false;
    if (name == null) {
      if (other.name != null) return false;
    } else if (!name.equals(other.name)) return false;
    if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price)) return false;
    return true;
  }
}
