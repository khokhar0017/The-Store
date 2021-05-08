package com.mycart.app.TheStore.Dao;

import java.util.List;
import com.mycart.app.TheStore.Model.Item;

public interface ItemDao {

  public List<Item> getAllItems();

  public Item getItemById();

  public List<Item> getItemByCategory();

  public void addItems(List<Item> items);

  public void addItem(Item item);

  public void deleteItems(List<Item> items);

  public void deleteItem(Item item);
}
