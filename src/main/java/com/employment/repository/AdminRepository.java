package com.employment.repository;

import com.employment.model.entity.AdminData;
import com.employment.model.request.AdminRequest;

import java.util.List;

public interface AdminRepository {
    AdminData getAdminById(Long id);
    List<AdminData> getAllAdmin();
    void createAdmin(AdminRequest request);
    void updateAdmin(Long id, AdminRequest request) throws Exception;
    void deleteAdmin(Long id);
}
