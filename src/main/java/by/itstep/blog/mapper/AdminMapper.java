package by.itstep.blog.mapper;

import by.itstep.blog.entity.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminMapper {

    public Admin extract(ResultSet rs) throws SQLException {
        return Admin.getBuilder()
                .setAdminId(rs.getLong("admin_id"))
                .setName(rs.getString("name"))
                .setEmail(rs.getString("email"))
                .setPassword(rs.getString("password"))
                .build();
    }
}
