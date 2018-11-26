package models.comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * CommentJDBCTemplate
 */
@Repository
public class CommentJDBCTemplate {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(Comment comment) {
        String sql = "INSERT INTO comment VALUES(?,?,?,?,?,?)";
        Object[] params = new Object[] { 0, comment.getAuthor(), comment.getDate(), comment.getText(),
                comment.getIdItem(), comment.getValoration() };
        jdbcTemplate.update(sql, params);
    }

    public List<Comment> findItemComments(int id) {
        String sql = "SELECT * FROM comment WHERE idItem = ?";
        List<Comment> comments = jdbcTemplate.query(sql, new Object[] { id }, new RowMapper<Comment>() {
            @Override
            public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
                Comment comment = new Comment();
                comment.setIdItem(rs.getInt("idItem"));
                comment.setAuthor(rs.getString("author"));
                comment.setDate(rs.getString("date"));
                comment.setText(rs.getString("text"));
                comment.setValoration(rs.getInt("valoration"));
                return comment;
            }
        });
        return comments;

    }

}