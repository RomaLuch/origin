package ru.mycash.cash.controller;

import ru.mycash.cash.model.Category;
import ru.mycash.cash.model.Record;
import ru.mycash.cash.service.CategoryService;
import ru.mycash.cash.service.CategoryServiceImpl;
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
    CategoryService categoryService = new CategoryServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("doget");

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "create":
            case "update":
                final Record record = "create".equalsIgnoreCase(action) ?
                        new Record(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "",categoryService.get(1), 1000) :
                        service.get(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("record", record);
                request.getRequestDispatcher("/record.jsp").forward(request, response);
                break;
            case "delete":
                Integer id = Integer.parseInt(request.getParameter("id"));
                service.delete(id);
                response.sendRedirect("records");
                break;
            case "all":
            default:
                System.out.println("action =" + action);
                List<Record> records = service.getAll();
                records.forEach(System.out::println);
                request.setAttribute("records", records);
                request.getRequestDispatcher("/records.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String category = request.getParameter("category_id");


        Record record = new Record(id.isEmpty()?null:Integer.parseInt(id),
                LocalDateTime.parse(request.getParameter("date")),
                request.getParameter("description"),
                categoryService.get(Integer.parseInt(category)),
                Integer.parseInt(request.getParameter("amount")));

            service.save(record);
        response.sendRedirect("records");
    }
}
