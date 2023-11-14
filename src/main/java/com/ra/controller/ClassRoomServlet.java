package com.ra.controller;

import com.ra.model.entity.ClassRoom;
import com.ra.model.service.ClassRoomService;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "ClassRoomServlet", value = "/class")
public class ClassRoomServlet extends HttpServlet {

    private ClassRoomService classRoomService;

    @Override
    public void init() {
        classRoomService = new ClassRoomService();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showClassList(req,resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void destroy() {

    }

    public void showClassList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ClassRoom> classRooms = classRoomService.findAll();
        req.setAttribute("classRooms", classRooms);
        req.getRequestDispatcher("views/class-room.jsp").forward(req, resp);
    }
}
