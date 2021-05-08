package com.mycart.app.TheStore.Dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.mycart.app.TheStore.Model.Item;

@Component
@Qualifier("itemRepository")
public class ItemDaoImpl implements ItemDao {

  private final String INSERT =
      "INSERT INTO item (id, name, category, price , brand ) VALUES (?, ?, ?, ?, ?)";
  private final String SELECT_BY_ID = "SELECT * FROM item WHERE ID = ? ";
  private final String SELECT_BY_CATEGORY = "SELECT * FROM item WHERE CATEGORY = ? ";
  private final String SELECT_ALL = "SELECT * FROM item";
  private final String DELETE = "DELETE FROM item WHERE ID = ?";

  private final int BATCH_SIZE = 20;

  @Autowired JdbcTemplate jdbcTemplate;

  @Override
  public List<Item> getAllItems() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Item getItemById() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Item> getItemByCategory() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void addItems(final List<Item> items) {
    jdbcTemplate.batchUpdate(
        INSERT,
        new BatchPreparedStatementSetter() {
          @Override
          public void setValues(final PreparedStatement ps, final int i) throws SQLException {
            ps.setString(1, items.get(i).getId());
            ps.setString(2, items.get(i).getName());
            ps.setString(3, items.get(i).getCategory().toString());
            ps.setDouble(4, items.get(i).getPrice());
            ps.setString(5, items.get(i).getBrand());
          }

          @Override
          public int getBatchSize() {
            return BATCH_SIZE;
          }
        });
  }

  @Override
  public void addItem(final Item item) {
    jdbcTemplate.update(
        "INSERT INTO item (id, name, category, price , brand ) VALUES (?, ?, ?, ?, ?)",
        item.getId(),
        item.getName(),
        item.getBrand(),
        item.getPrice(),
        item.getBrand());
  }

  @Override
  public void deleteItems(final List<Item> items) {
    // TODO Auto-generated method stub

  }

  @Override
  public void deleteItem(final Item item) {
    // TODO Auto-generated method stub

  }
}
