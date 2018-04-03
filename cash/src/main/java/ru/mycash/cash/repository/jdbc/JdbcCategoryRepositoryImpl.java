package ru.mycash.cash.repository.jdbc;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.mycash.cash.model.Category;
import ru.mycash.cash.repository.CategoryRepository;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;


@Repository
public class JdbcCategoryRepositoryImpl implements CategoryRepository {

    private static final Logger log = getLogger(JdbcCategoryRepositoryImpl.class);

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
        log.info("!!!!!category = {}", category);
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", category.getId())
                .addValue("name", category.getName())
                .addValue("user_id", userId);

        if (category.isNew()) {
            log.info("save");
            Number newId = insertCategory.executeAndReturnKey(map);
            category.setId(newId.intValue());
        } else {
            log.info("update");
            if (namedParameterJdbcTemplate.update("" +
                            "UPDATE categories " +
                            "   SET id=:id, name=:name, user_id=:user_id" +
                            " WHERE id=:id AND user_id=:user_id"
                    , map) == 0) {
                return null;
            }
        }
        return category;
    }

    @Override
    public boolean delete(Integer id, Integer userId) {
        return jdbcTemplate.update("DELETE FROM categories WHERE id=? AND user_id=?", id, userId) != 0;
    }

    @Override
    public Category get(Integer id, Integer userId) {
        log.info("get CategoryId({} UserID({}))", id, userId);
        List<Category> categories = jdbcTemplate.query(
                "SELECT * FROM categories WHERE id = ? AND user_id = ?", ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(categories);
    }

    @Override
    public Collection<Category> getAll(Integer userId) {
        log.info("getAll");
        return jdbcTemplate.query("SELECT * FROM categories WHERE user_id = ? ORDER BY name", ROW_MAPPER, userId);
    }
}
