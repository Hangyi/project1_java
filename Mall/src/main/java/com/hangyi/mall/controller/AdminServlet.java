package com.hangyi.mall.controller;

import com.google.gson.Gson;
import com.hangyi.mall.bean.Admin;
import com.hangyi.mall.bean.Result;
import com.hangyi.mall.service.AdminService;
import com.hangyi.mall.service.impl.AdminServiceImpl;
import com.hangyi.mall.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/api/admin/admin/*")
public class AdminServlet extends HttpServlet {

    private AdminService adminService = new AdminServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //注册,登陆是post请求
        System.out.println("post");
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/admin/", "");
        System.out.println(action);

        if("login".equals(action)){
            login(request, response);
        }else if("addAdminss".equals(action)){
            addAdminss(request, response);
        }
    }

    /**
     * 登录的具体业务逻辑实现
     * 思路：从请求体中获取到参数
     *      解析出admin对象
     *      数据库查询结果
     *      返回结果
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);

//        将下面代码提取出来放到utils中
//        ServletInputStream inputStream = request.getInputStream();
//        OutputStream outputStream = new ByteArrayOutputStream();
//        int length = 0;
//        byte[] bytes = new byte[1024];
//        while ((length = inputStream.read(bytes)) != -1){
//            outputStream.write(bytes, 0, length);
//        }

        //该字符串就是我们的请求体
//        String requestBody = outputStream.toString();
        System.out.println(requestBody);

        Gson gson = new Gson();
        Admin admin = gson.fromJson(requestBody, Admin.class);
//        System.out.println(admin);


        //调用service层
        int result = adminService.login(admin);
        Result res = new Result();
        if(result == 200){
            res.setCode(0);
            Map<String, String> map = new HashMap<>();
            map.put("token", admin.getEmail());
            map.put("name", admin.getNickname());
            res.setData(map);
        }else if(result == 404){
            res.setCode(10000);
            res.setMessage("用户名或密码错误!");
        }else{
            res.setCode(10000);
            res.setMessage("当前访问异常, 请稍后重试");
        }

        //接下来一json字符串的形式返回,写入响应体里面
        response.getWriter().println(gson.toJson(res));

    }

    private void addAdminss(HttpServletRequest request, HttpServletResponse response) {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("get");
    }
}
