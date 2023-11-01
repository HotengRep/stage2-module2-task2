package com.example.servlet;

import com.example.Users;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path;
        HttpSession httpSession = req.getSession();
        String userName = (String) httpSession.getAttribute("user");

        if(userName == null)
         {
              path = "/login.jsp";
         }
         else {
              path = "/user/hello.jsp";
         };
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
        requestDispatcher.forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> list = Users.getInstance().getUsers();
        String path;
        if(list.contains(req.getParameter("login")) && req.getParameter("password").equals(""))
        {
            req.getSession().setAttribute("user",req.getParameter("login"));

            path = "/user/hello.jsp";
        }else {
            path = "/login.jsp";
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
        requestDispatcher.forward(req,resp);

    }
}
