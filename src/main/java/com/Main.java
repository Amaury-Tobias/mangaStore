package com;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import models.comment.CommentJDBCTemplate;
import models.item.ItemJDBCTemplate;
import user.UserJDBCTemplate;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }
/*
    @RequestMapping("/db")
    String db(Map<String, Object> model) {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
            stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
            ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

            ArrayList<String> output = new ArrayList<String>();
            while (rs.next()) {
                output.add("Read from DB: " + rs.getTimestamp("tick"));
            }

            model.put("records", output);
            return "db";
        } catch (Exception e) {
            model.put("message", e.getMessage());
            return "error";
        }
    }
*/
    @Value("${spring.datasource.url}")
    private String dbUrl = "jdbc:mysql://vahf5voe2w0xln5z:osenmng86mplly41@yhrz9vns005e0734.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/mxbrv4djh5ub33r4";

    @Bean
    public DataSource dataSource() throws SQLException {
        if (dbUrl == null || dbUrl.isEmpty()) {
            return new HikariDataSource();
        } else {
            HikariConfig config = new HikariConfig();
            //config.setDriverClassName("com.mysql.");
            config.setJdbcUrl(dbUrl);
            config.setIdleTimeout(1000*60*5);
            config.setMinimumIdle(2);
            config.setMaximumPoolSize(8);
            return new HikariDataSource(config);
        }
    }

    @Bean
    UserJDBCTemplate userJDBCTemplate()
    {
        return new UserJDBCTemplate();
    }

    @Bean
    ItemJDBCTemplate itemJDBCTemplate()
    {
        return new ItemJDBCTemplate();
    }

    @Bean
    CommentJDBCTemplate commentJDBCTemplate(){
        return new CommentJDBCTemplate();
    }

}
