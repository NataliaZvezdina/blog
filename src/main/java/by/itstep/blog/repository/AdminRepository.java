package by.itstep.blog.repository;

import by.itstep.blog.entity.Admin;

public interface AdminRepository {

    Admin findByEmail(String email);

}
