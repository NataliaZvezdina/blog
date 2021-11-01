package by.itstep.blog.repository.impl;

import by.itstep.blog.entity.Admin;
import by.itstep.blog.mapper.AdminMapper;
import by.itstep.blog.repository.AdminRepository;
import by.itstep.blog.util.ConnectionUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AdminRepositoryImpl implements AdminRepository {

    private AdminMapper adminMapper = new AdminMapper();

    private static final String FIND_BY_EMAIL = "SELECT * FROM admin " +
            "WHERE email=?;";

    @Override
    public Admin findByEmail(String email) {
        System.out.println(email);
        try (Connection con = ConnectionUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(FIND_BY_EMAIL)) {

            pstmt.setString(1, email);

            try (ResultSet rs = pstmt.executeQuery()) {
                Admin found = null;
                if (rs.next()) {
                    found = adminMapper.extract(rs);
                }
                return found;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while getting admin", e);
        }
    }
}
