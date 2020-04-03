package com.hangyi.mall.dao.impl;

import com.hangyi.mall.bean.Admin;
import com.hangyi.mall.dao.AdminDao;
import com.hangyi.mall.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * @author ZhuHangYi
 * @create 2020-04-02 20:33
 */


public class AdminDaoImpl implements AdminDao{

    @Override
    public int login(Admin admin) {
        //sql
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Admin query = null;
        try {
            query = runner.query("select * from admin where email = ? and pwd = ?",
                    new BeanHandler<>(Admin.class),
                    admin.getEmail(),
                    admin.getPwd());
        } catch (SQLException e) {
            e.printStackTrace();
            return 500;
        }

        if(query != null){
            return 200;
        }
        return 404;
    }
}
