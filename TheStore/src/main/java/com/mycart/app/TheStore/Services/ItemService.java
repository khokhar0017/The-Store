package com.mycart.app.TheStore.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mycart.app.TheStore.Dao.ItemDao;
import com.mycart.app.TheStore.Model.Item;

@Service
public class ItemService {

  @Autowired private ItemDao dataAccess;

  public List<Item> getAllItems() {
    return dataAccess.getAllItems();
  }

  public Item getItemById(final Long id) {
    return dataAccess.getItemById(id);
  }

  public List<Item> getItemByCategory(final String category) {
    return dataAccess.getItemByCategory(category);
  }

  public void addItems(final List<Item> items) {
    if (items.size() == 1) dataAccess.addItem(items.get(0));
    else dataAccess.addItems(items);
  }

  public void deleteItems(final List<Long> ids) {
    if (ids.size() == 1) dataAccess.deleteItem(ids.get(0));
    else dataAccess.deleteItems(ids);
  }
}
