package ru.mycash.cash.controller;

import ru.mycash.cash.model.Record;
import ru.mycash.cash.service.Service;
import ru.mycash.cash.service.ServiseImpl;
import ru.mycash.cash.util.TimeUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class Controller extends HttpServlet{

    Service service = new ServiseImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

if (action==null) {
    List<Record> records = service.getAll();
    request.setAttribute("records", records);
    request.getRequestDispatcher("/records.jsp").forward(request, response);
}
else if("create".equalsIgnoreCase(action))
    {
    request.getRequestDispatcher("/record.jsp").forward(request,response);
    }
    else if("update".equalsIgnoreCase(action))
    {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Record record = service.get(id);
        request.setAttribute("record", record);
        request.getRequestDispatcher("/record.jsp").forward(request, response);
    }
    else if("delete".equalsIgnoreCase(action))
    {
        Integer id = Integer.parseInt(request.getParameter("id"));
        service.delete(id);
        List<Record> records = service.getAll();
        request.setAttribute("records", records);
        request.getRequestDispatcher("/records.jsp").forward(request, response);
    }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");


        LocalDateTime ldt = TimeUtil.stringToLocalDateTime(request.getParameter("date"));
        String description = request.getParameter("description");
        Integer amount = Integer.parseInt(request.getParameter("amount"));

        if (request.getParameter("id")!=null) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            Record record = service.get(id);
            record.setDateTimetime(ldt);
            record.setDescription(description);
            record.setAmount(amount);
            service.save(record);
        }
        else {
                Record record = new Record(ldt,description,amount);
                service.save(record);
            }

        List<Record> records = service.getAll();
        request.setAttribute("records", records);
        request.getRequestDispatcher("/records.jsp").forward(request, response);
    }
}
