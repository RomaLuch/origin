package ru.mycash.cash.controller;

import ru.mycash.cash.model.Record;
import ru.mycash.cash.service.Service;
import ru.mycash.cash.service.ServiseImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
else if("create".equalsIgnoreCase(action) || "update".equalsIgnoreCase(action))
    {
        final Record record = "create".equalsIgnoreCase(action)?
                new Record(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000):
                service.get(Integer.parseInt(request.getParameter("id")));
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
        String id = request.getParameter("id");

        Record record = new Record(id.isEmpty()?null:Integer.parseInt(id),
                LocalDateTime.parse(request.getParameter("date")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("amount")));

            service.save(record);
        response.sendRedirect("records");
    }
}
