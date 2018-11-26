package models.item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ItemJDBCTemplate implements JDBCTemplateInt<Item> {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Item> findAll() {
        String sql = "SELECT * FROM item WHERE active = 1";
        List<Item> items = jdbcTemplate.query(sql, new RowMapper<Item>() {
            @Override
            public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
                Item item = new Item();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getFloat("price"));
                item.setValoration(rs.getInt("valoration"));
                item.setSearchedTimes(rs.getInt("searchedTimes"));
                item.setSearchedTimes(rs.getInt("viewedTimes"));
                item.setOwner(rs.getString("owner"));


                // item.setPicture1(rs.getBytes("picture1"));
                // item.setPicture2(rs.getBytes("picture2"));
                // item.setPicture2(rs.getBytes("picture3"));

                return item;
            }
        });
        return items;
    }

    public void addToCart(int id, String user) {
        String sql = "INSERT INTO cart VALUES(?,?)";
        Object[] params = new Object[] { user, id };
        jdbcTemplate.update(sql, params);
    }

    public void removeCart(int id, String user) {
        String sql = "DELETE FROM cart WHERE idUser LIKE \"%" + user + "%\" AND idItem = " + id;
        jdbcTemplate.update(sql);
    }

    public List<Item> findAllCategory(String category) {
        String sql = "SELECT * FROM item WHERE category LIKE '%" + category + "%' AND active = 1";
        List<Item> items = jdbcTemplate.query(sql, new RowMapper<Item>() {
            @Override
            public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
                Item item = new Item();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getFloat("price"));
                item.setValoration(rs.getInt("valoration"));
                item.setSearchedTimes(rs.getInt("searchedTimes"));
                item.setSearchedTimes(rs.getInt("viewedTimes"));
                item.setOwner(rs.getString("owner"));

                return item;
            }
        });
        return items;
    }

    public List<Item> findhighlights() {
        String sql = "SELECT item.*, AVG(comment.valoration) AS rate FROM item JOIN comment ON item.id = comment.idItem GROUP BY item.id ORDER BY searchedTimes desc LIMIT 6";
        List<Item> items = jdbcTemplate.query(sql, new RowMapper<Item>() {
            @Override
            public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
                Item item = new Item();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getFloat("price"));
                item.setValoration(rs.getInt("rate"));
                item.setSearchedTimes(rs.getInt("searchedTimes"));
                item.setSearchedTimes(rs.getInt("viewedTimes"));
                item.setOwner(rs.getString("owner"));

                return item;
            }
        });
        return items;
    }

    public void create(Item item, String user) {
        String sql = "INSERT INTO item VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        Object[] params = new Object[] { 0, item.getName(), item.getDescription(), item.getCategory(), item.getPrice(),
                item.getValoration(), item.getSearchedTimes(), item.getViewedTimes(), item.getPicture1(),
                item.getPicture2(), item.getPicture3(), "", item.isActive(), user };
        jdbcTemplate.update(sql, params);

    }

    public List<Item> getCartItems(String user) {
        String sql = "SELECT item.* FROM item INNER JOIN cart ON item.id = cart.idItem AND cart.idUser LIKE '%" + user
                + "%'";
        List<Item> items = jdbcTemplate.query(sql, new RowMapper<Item>() {
            @Override
            public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
                Item item = new Item();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getFloat("price"));
                item.setValoration(rs.getInt("valoration"));
                item.setSearchedTimes(rs.getInt("searchedTimes"));
                item.setSearchedTimes(rs.getInt("viewedTimes"));
                item.setOwner(rs.getString("owner"));

                return item;
            }
        });
        return items;
    }

    public Item getItemImage(int id) {
        String sql = "SELECT * FROM item WHERE id = ?";
        if (id == 0) {
            return new Item();
        }
        return jdbcTemplate.queryForObject(sql, new Object[] { id }, new RowMapper<Item>() {
            @Override
            public models.item.Item mapRow(ResultSet rs, int rowNum) throws SQLException {
                Item item = new Item();
                item.setPicture1(rs.getBytes("picture1"));
                item.setPicture2(rs.getBytes("picture2"));
                item.setPicture3(rs.getBytes("picture3"));
                return item;
            }
        });
    }

    public Item getItemById(int id) {
        String sql = "SELECT item.*, AVG(comment.valoration) AS rate FROM item JOIN comment ON item.id = ? AND comment.idItem = ? GROUP BY item.id";

        if (id == 0) {
            return new Item();
        }
        return jdbcTemplate.queryForObject(sql, new Object[] { id, id }, new RowMapper<Item>() {
            @Override
            public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
                Item item = new Item();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getFloat("price"));
                item.setValoration(rs.getInt("rate"));
                item.setSearchedTimes(rs.getInt("searchedTimes"));
                item.setSearchedTimes(rs.getInt("viewedTimes"));
                item.setCategory(rs.getString("category"));
                item.setOwner(rs.getString("owner"));

                return item;
            }
        });
    }

    public List<Item> search(String category) {
        String sql = "SELECT * FROM item WHERE " 
        + "name LIKE '%" + category + "%' OR description LIKE '%" + category
        + "%' AND active = 1";

        List<Item> items = jdbcTemplate.query(sql, new RowMapper<Item>() {
            @Override
            public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
                Item item = new Item();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getFloat("price"));
                item.setValoration(rs.getInt("valoration"));
                item.setSearchedTimes(rs.getInt("searchedTimes"));
                item.setSearchedTimes(rs.getInt("viewedTimes"));
                item.setOwner(rs.getString("owner"));
                return item;
            }
        });
        return items;
    }

    public void checkout(String user) {
        String sql = "DELETE FROM cart WHERE idUser LIKE \"%" + user + "%\"";
        jdbcTemplate.update(sql);
    }
}