package ru.mycash.cash.controller;

import org.slf4j.Logger;
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

import static org.slf4j.LoggerFactory.getLogger;


public class Controller extends HttpServlet{
private static final Logger log = getLogger(Controller.class);
    //private static final Logger log = getLogger(Controller.class);
    Service service = new ServiseImpl();
    CategoryService categoryService = new CategoryServiceImpl();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("doGet");
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "create":
            case "update":
                log.info(action);
                String catid = request.getParameter("category_id");
                log.info("category id ({})", catid);
                //Integer categoryId = Integer.parseInt(request.getParameter("category_id"));
                final Record record = "create".equalsIgnoreCase(action) ?
                        new Record(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "",categoryService.get(1), 1000) :
                        service.get(Integer.parseInt(request.getParameter("id")));


                request.setAttribute("categorys", categoryService.getAll());
                request.setAttribute("record", record);
                request.getRequestDispatcher("/record.jsp").forward(request, response);
                break;
            case "delete":
                log.info("delete");
                Integer id = Integer.parseInt(request.getParameter("id"));
                service.delete(id);
                response.sendRedirect("records");
                break;
            case "all":
            default:
                log.info("All");
                List<Record> records = service.getAll();
                List<Category> categories = categoryService.getAll();
                request.setAttribute("records", records);
                request.setAttribute("categories", categories);
                request.getRequestDispatcher("/records.jsp").forward(request, response);
                break;
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String category = request.getParameter("category_id");
log.info("DOPOST category_id({})", category);

        Record record = new Record(id.isEmpty()?null:Integer.parseInt(id),
                LocalDateTime.parse(request.getParameter("date")),
                request.getParameter("description"),
                categoryService.get(Integer.parseInt(category)),
                Integer.parseInt(request.getParameter("amount")));

            service.save(record);
        response.sendRedirect("records");
    }
}
