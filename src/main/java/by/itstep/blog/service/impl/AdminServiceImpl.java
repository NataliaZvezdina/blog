package by.itstep.blog.service.impl;

import by.itstep.blog.dto.admin.AdminSignInDto;
import by.itstep.blog.entity.Admin;
import by.itstep.blog.repository.AdminRepository;
import by.itstep.blog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public boolean checkIfValid(AdminSignInDto request) {
        Admin toCompare = adminRepository.findByEmail(request.getEmail());

        if (toCompare == null) {
            return false;
        }

        if (!toCompare.getPassword().equals(request.getPassword())) {
            return false;
        }
        return true;
    }
}
