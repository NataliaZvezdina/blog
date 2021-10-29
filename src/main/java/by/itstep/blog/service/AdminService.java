package by.itstep.blog.service;

import by.itstep.blog.dto.admin.AdminSignInDto;

public interface AdminService {

    boolean checkIfValid(AdminSignInDto request);
}
