package user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserJDBCTemplate {
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(Connection connection){
        //this.jdbcTemplate = new JdbcTemplate((DataSource) connection);
        this.jdbcTemplate = new JdbcTemplate(
                new SingleConnectionDataSource(connection, false)
        );
    }

    public User getUser(Integer id){
        String SQL = "SELECT * FROM user WHERE id = ?";
        User user = jdbcTemplate.queryForObject(SQL, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                return user;
            }
        }, id);
        return user;
    }

}
