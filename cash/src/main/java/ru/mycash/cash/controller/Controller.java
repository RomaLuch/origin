package ru.mycash.cash.controller;

import ru.mycash.cash.dao.Repository;
import ru.mycash.cash.model.Record;
import ru.mycash.cash.service.Service;
import ru.mycash.cash.service.ServiseImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Controller extends HttpServlet{

    Service service = new ServiseImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Record> records = service.getAll();
        request.setAttribute("records", records);
        request.getRequestDispatcher("/records.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
