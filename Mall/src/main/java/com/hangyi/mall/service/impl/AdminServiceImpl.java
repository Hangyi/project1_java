package com.hangyi.mall.service.impl;

import com.hangyi.mall.bean.Admin;
import com.hangyi.mall.dao.AdminDao;
import com.hangyi.mall.dao.impl.AdminDaoImpl;
import com.hangyi.mall.service.AdminService;

/**
 * @author ZhuHangYi
 * @create 2020-04-02 20:27
 */


public class AdminServiceImpl implements AdminService {

   private AdminDao adminDao = new AdminDaoImpl();

    @Override
    public int login(Admin admin) {
        return adminDao.login(admin);
    }
}
