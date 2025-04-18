package com.employment.service.impl;

import com.employment.form.CreateAdminForm;
import com.employment.mapper.AdminMap;
import com.employment.model.entity.AdminData;
import com.employment.model.request.AdminRequest;
import com.employment.repository.AdminRepository;
import com.employment.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AdminMap adminMap;

    @Override
    public AdminData getAdminById(Long id) {
        return adminRepository.getAdminById(id);
    }

    @Override
    public List<AdminData> getAllAdmin() {
        return adminRepository.getAllAdmin();
    }

    @Override
    public void createAdmin(AdminRequest request) {
        adminRepository.createAdmin(request);
    }

    @Override
    public void updateAdmin(Long id, AdminRequest request) throws Exception {
        adminRepository.updateAdmin(id, request);
    }

    @Override
    public void deleteAdmin(Long id) {
        adminRepository.deleteAdmin(id);
    }

    @Override
    public void registerAdmin(CreateAdminForm form) {
        AdminRequest request = adminMap.map(form);
        adminRepository.createAdmin(request);
    }
}
