package com.employment.service;

import com.employment.form.CreateAdminForm;
import com.employment.model.entity.AdminData;
import com.employment.model.request.AdminRequest;

import java.util.List;

public interface AdminService {
    AdminData getAdminById(Long id);
    List<AdminData> getAllAdmin();
    void createAdmin(AdminRequest request);
    void updateAdmin(Long id, AdminRequest request) throws Exception;
    void deleteAdmin(Long id);
    void registerAdmin(CreateAdminForm form);
}
