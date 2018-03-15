package ru.mycash.cash.web;

import org.slf4j.Logger;
import ru.mycash.cash.controller.RecordRestController;
import ru.mycash.cash.model.Category;
import ru.mycash.cash.model.Record;
import ru.mycash.cash.util.RecordsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by RLuchinsky on 15.03.2018.
 */
public class RecordServlet extends HttpServlet {
    private static final Logger log = getLogger(RecordServlet.class);
    RecordRestController controller = new RecordRestController ();
   // CategoryService categoryService = new CategoryServiceImpl();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        log.info("doGet action = {}", action);


        switch (action == null ? "all" : action) {
            case "create":
            case "update":
                log.info(action);
                String catid = request.getParameter("category_id");
                log.info("category id ({})", catid);
                final Record record = "create".equalsIgnoreCase(action) ?
                        new Record(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "",controller.getCategory(1), 1000) :
                        controller.get(Integer.parseInt(request.getParameter("id")));


                request.setAttribute("categorys", controller.getAllCategories());
                request.setAttribute("record", record);
                request.getRequestDispatcher("/record.jsp").forward(request, response);
                break;
            case "createCategory":
                log.info("createCategory");
                Category createCategory = new Category("");
                request.setAttribute("category", createCategory);
                request.getRequestDispatcher("/category.jsp").forward(request, response);
                break;
            case "delete":
                log.info("delete");
                Integer id = Integer.parseInt(request.getParameter("id"));
                controller.delete(id);
                response.sendRedirect("records");
                break;
            case "all":
            default:
                log.info("All");
                List<Record> records = controller.getAll();
                List<Category> categories = controller.getAllCategories();
                Integer total = RecordsUtil.getTotal(records);
                categories.stream().forEach(category -> log.info("categoryId({}) categoryName [{}]",category.getId(),category.getName()));
                request.setAttribute("records", records);
                request.setAttribute("categories", categories);
                request.setAttribute("total", total);
                request.getRequestDispatcher("/records.jsp").forward(request, response);
                break;
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        if (request.getParameter("add_categoryName") != null) {
            String id = request.getParameter("add_categoryId");
            String name = request.getParameter("add_categoryName");
            Category category = new Category(name);
            controller.createCategory(category);
            response.sendRedirect("records");
            return;
        } else {
            String id = request.getParameter("id");
            String category = request.getParameter("category_id");
            log.info("DOPOST category_id({})", category);

            Record record = new Record(id.isEmpty() ? null : Integer.parseInt(id),
                    LocalDateTime.parse(request.getParameter("date")),
                    request.getParameter("description"),
                    controller.getCategory(Integer.parseInt(category)),
                    Integer.parseInt(request.getParameter("amount")));
            if (record.isNew()) {
                controller.create(record);
            } else controller.update(record);
            response.sendRedirect("records");
        }
    }
}
