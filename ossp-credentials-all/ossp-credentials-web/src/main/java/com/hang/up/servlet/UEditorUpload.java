package com.hang.up.servlet;

import com.hang.up.util.baidu.ueditor.ActionEnter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 顺 on 2018/12/16.
 */

public class UEditorUpload extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doFilterInternal(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doFilterInternal(req,resp);
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type" , "text/html");
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        PrintWriter out = response.getWriter();
        out.write( new ActionEnter( request, rootPath).exec());
        out.close();
    }
}
