package ru.mycash.cash.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.mycash.cash.model.Category;
import ru.mycash.cash.repository.CategoryRepository;

import javax.sql.DataSource;
import java.util.Collection;

/**
 * Created by RLuchinsky on 02.04.2018.
 */
@Repository
public class JdbcCategoryRepositoryImpl implements CategoryRepository {

    private static final BeanPropertyRowMapper<Category> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Category.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertCategory;

    @Autowired
    public JdbcCategoryRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertCategory = new SimpleJdbcInsert(dataSource)
                .withTableName("categories")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Category save(Category category, Integer userId) {
        return null;
    }

    @Override
    public boolean delete(Integer id, Integer userId) {
        return false;
    }

    @Override
    public Category get(Integer id, Integer userId) {
        return null;
    }

    @Override
    public Collection<Category> getAll(Integer userId) {
        return jdbcTemplate.query("SELECT * FROM categories ORDER BY name", ROW_MAPPER);
    }
}
