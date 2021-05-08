package com.mycart.app.TheStore.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
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
    final List<Item> items = jdbcTemplate.query(SELECT_ALL, new ItemMapper());
    return items;
  }

  @Override
  public Item getItemById(final Long id) {
    final Item item =
        jdbcTemplate.queryForObject(SELECT_BY_ID, new Object[] {id}, new ItemMapper());
    return item;
  }

  @Override
  public List<Item> getItemByCategory(final String category) {
    final List<Item> items =
        jdbcTemplate.query(SELECT_BY_CATEGORY, new ItemMapper(), new Object[] {category});
    return items;
  }

  @Override
  public void addItems(final List<Item> items) {
    jdbcTemplate.batchUpdate(
        INSERT,
        new BatchPreparedStatementSetter() {
          @Override
          public void setValues(final PreparedStatement ps, final int i) throws SQLException {
            ps.setLong(1, items.get(i).getId());
            ps.setString(2, items.get(i).getName());
            ps.setString(3, items.get(i).getCategory());
            ps.setDouble(4, items.get(i).getPrice());
            ps.setString(5, items.get(i).getBrand());
          }

          @Override
          public int getBatchSize() {
            return items.size();
          }
        });
  }

  @Override
  public void addItem(final Item item) {
    jdbcTemplate.update(
        INSERT,
        new PreparedStatementSetter() {
          @Override
          public void setValues(final PreparedStatement ps) throws SQLException {
            // ps.setLong(1, item.getId());
            ps.setString(2, item.getName());
            ps.setString(3, item.getCategory());
            ps.setDouble(4, item.getPrice());
            ps.setString(5, item.getBrand());
          }
        });
  }

  @Override
  public void deleteItems(final List<Long> ids) {
    jdbcTemplate.batchUpdate(
        DELETE,
        new BatchPreparedStatementSetter() {
          @Override
          public void setValues(final PreparedStatement ps, final int i) throws SQLException {
            ps.setLong(1, ids.get(i));
          }

          @Override
          public int getBatchSize() {
            return BATCH_SIZE;
          }
        });
  }

  @Override
  public void deleteItem(final Long id) {
    jdbcTemplate.update(
        DELETE,
        new PreparedStatementSetter() {
          @Override
          public void setValues(final PreparedStatement ps) throws SQLException {
            ps.setLong(1, id);
          }
        });
  }

  public class ItemMapper implements RowMapper<Item> {
    @Override
    public Item mapRow(final ResultSet rs, final int rowNum) throws SQLException {
      final Item item =
          new Item.Builder()
              .id(rs.getLong("id"))
              .name(rs.getString("name"))
              .category(rs.getString("category"))
              .price(rs.getDouble("price"))
              .brand(rs.getString("brand"))
              .build();
      return item;
    }
  }
}
