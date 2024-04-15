package org.example.service;

import org.example.container.Container;
import org.example.dao.AdminDao;
import org.example.dao.MemberDao;
import org.example.dto.Admin;
import org.example.dto.Member;

public class AdminService {
    private static AdminDao adminDao;

    public AdminService() {
        adminDao = Container.adminDao;
    }


    public Admin getAdminByLoginId(String loginId) {
        return adminDao.getAdminByLoginId(loginId);
    }

    public static void StringUpdate(String Info, String changeInfo, int id) {
        adminDao.StringUpdate(Info, changeInfo, id);
    }
}