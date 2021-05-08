package com.mycart.app.TheStore.Dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.mycart.app.TheStore.Model.Item;

@Repository
public interface ItemDao {

  public List<Item> getAllItems();

  public Item getItemById(Long id);

  public List<Item> getItemByCategory(String category);

  public void addItems(List<Item> items);

  public void addItem(Item item);

  public void deleteItems(List<Long> ids);

  public void deleteItem(Long id);
}
