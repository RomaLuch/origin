package ru.mycash.cash.web;

import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.mycash.cash.controller.RecordRestController;
import ru.mycash.cash.model.Category;
import ru.mycash.cash.model.Record;
import ru.mycash.cash.util.RecordsUtil;
import ru.mycash.cash.util.TimeUtil.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.mycash.cash.util.TimeUtil.*;

public class RecordServlet extends HttpServlet {
    private static final Logger log = getLogger(RecordServlet.class);

    private ConfigurableApplicationContext springContext;
    RecordRestController controller;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        controller = springContext.getBean(RecordRestController.class);
    }

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
                Category def = controller.getAllCategories()
                        .stream()
                        .filter(category -> "default".equalsIgnoreCase(category.getName()))
                        .findAny()
                        .orElse(null);
                if (def == null) def = controller.getAllCategories().
                        stream()
                        .findFirst()
                        .get();


                final Record record = "create".equalsIgnoreCase(action) ?
                        new Record(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "",def, 1000) ://todo
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
                Category def_from_all = categories
                        .stream()
                        .filter(category -> "default".equalsIgnoreCase(category.getName()))
                        .findAny()
                        .orElse(null);
                if(def_from_all==null) controller.createCategory(new Category("default"));
/*
                String filter = request.getParameter("category_id");

                records = (filter==null)?records:
                        records
                .stream()
                .filter(record1 -> record1.getCategory().getId().equals(Integer.valueOf(filter)))
                .collect(Collectors.toList());*/

                categories =controller.getAllCategories();
records.stream().forEach(System.out::println);
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

        String action = request.getParameter("action");

        if (request.getParameter("add_categoryName") != null) {
            String id = request.getParameter("add_categoryId");
            String name = request.getParameter("add_categoryName");
            Category category = new Category(name);
            controller.createCategory(category);
            response.sendRedirect("records");
            return;
        }
            else if ("filter".equals(action)) {

                LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
                      startDate = Objects.isNull(startDate)? MIN_DATE:startDate;
                LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
                      endDate = Objects.isNull(endDate)? MAX_DATE:endDate;
                LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
                      startTime = Objects.isNull(startTime)?MIN_TIME:startTime;
                LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
                        endTime = Objects.isNull(endTime)?MAX_TIME:endTime
                        ;

                Integer category_to_filter = Integer.valueOf(request.getParameter("category_id_to_filter"));
                List<Record> records = controller.getAllFiltred(startDate,endDate,startTime,endTime,category_to_filter);

                Integer total = RecordsUtil.getTotal(records);
                List<Category> categories = records
                        .stream()
                        .map(Record::getCategory)
                        .distinct()
                        .collect(Collectors.toList());

                request.setAttribute("records", records);

                request.setAttribute("categories", categories);
                request.setAttribute("total", total);

                request.getRequestDispatcher("/records.jsp").forward(request, response);
        }

         else {
            String id = request.getParameter("id");
            String category = request.getParameter("category_id");
            log.info("DOPOST category_id({})", category);

            Record record = new Record(id.isEmpty() ? null : Integer.parseInt(id),
                    LocalDateTime.parse(request.getParameter("date")),
                    request.getParameter("description"),
                    controller.getCategory(Integer.parseInt(category)),
                    Integer.parseInt(request.getParameter("amount")));

            System.out.println("RECORD!!!!!!!!!!" + record);

            if (record.isNew()) {
                controller.create(record);
            } else controller.update(record);
            response.sendRedirect("records");
        }
    }
}
