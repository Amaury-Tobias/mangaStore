package user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UserJDBCTemplate {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserJDBCTemplate() {

    }

    public void newUser(User user){
        String sql = "INSERT INTO users VALUES (?,?,?,?,?,?,?,?,?,?)";
        String sql2 = "INSERT INTO authorities VALUES (?,?)";
        Object[] params2 = new Object[] {user.getUserName(), "ROLE_USER"};
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Object[] params = new Object[] {user.getUserName(), user.getPassword(), true, user.getEmail(),
                user.getName(), user.getLastName(), user.getPhone(), user.getAddress(),
                user.getAvatar(), user.getCover()};
        jdbcTemplate.update(sql,params);
        jdbcTemplate.update(sql2,params2);
    }

    public User getUser(String userName){
        String SQL = "SELECT * FROM users WHERE USERNAME = ?";

        return jdbcTemplate.queryForObject(SQL, new Object[]{userName} ,new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setUserName(rs.getString("USERNAME"));
                user.setEmail(rs.getString("EMAIL"));
                user.setName(rs.getString("NAME"));
                user.setLastName(rs.getString("LASTNAME"));
                user.setPhone(rs.getString("PHONE"));
                user.setAddress(rs.getString("ADDRESS"));

                user.setAvatar(rs.getBytes("AVATAR"));
                user.setCover(rs.getBytes("COVER"));
                return user;
            }
        });
    }

}
