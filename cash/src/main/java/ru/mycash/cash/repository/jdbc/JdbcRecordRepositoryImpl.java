package ru.mycash.cash.repository.jdbc;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.mycash.cash.model.Category;
import ru.mycash.cash.model.Record;
import ru.mycash.cash.repository.RecordRepository;
import ru.mycash.cash.util.TimeUtil;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Repository
public class JdbcRecordRepositoryImpl implements RecordRepository {

    private static final Logger log = getLogger(JdbcRecordRepositoryImpl.class);

    private static final RowMapper<Record> ROW_MAPPER = (resultSet, i) -> {

        Category category = new Category(resultSet.getInt(7),resultSet.getString(8));

        Record record = new Record(resultSet.getInt(1),
                TimeUtil.parseTmeStamp(resultSet.getString(2)),
                resultSet.getString(3),
                category,
                resultSet.getInt(4));

        return record;
    };

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertRecord;

    @Autowired
    public JdbcRecordRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertRecord = new SimpleJdbcInsert(dataSource)
                .withTableName("records")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Record save(Record record, Integer userId) {


        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", record.getId())
                .addValue("datetime", record.getDateTime())
                .addValue("description", record.getDescription())
                .addValue("amount", record.getAmount())
                .addValue("user_id", userId)
                .addValue("category_id", record.getCategory().getId());

        if (record.isNew()) {
            log.info("save");
            Number newId = insertRecord.executeAndReturnKey(map);
            record.setId(newId.intValue());
        } else {
            log.info("update");
            if (namedParameterJdbcTemplate.update("" +
                            "UPDATE records " +
                            "   SET description=:description, datetime=:datetime, amount=:amount," +
                            "category_id = :category_id"+
                            " WHERE id=:id AND user_id=:user_id"
                    , map) == 0) {
                return null;
            }
        }
        return record;
    }

    @Override
    public boolean delete(Integer id, Integer userId) {
        return jdbcTemplate.update("DELETE FROM records WHERE id=? AND user_id=?", id, userId) != 0;
    }

    @Override
    public Record get(Integer id, Integer userId) {
        log.info("get RecordId({}) RecordID({}))", id, userId);
        List<Record> records = jdbcTemplate.query(
                "SELECT * FROM records LEFT JOIN categories ON (records.category_id = categories.id) WHERE records.id = ? AND records.user_id = ?", ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(records);
    }

    @Override
    public Collection<Record> getAll(Integer userId) {
        return jdbcTemplate.query(
                "SELECT * FROM records  LEFT JOIN categories ON (records.category_id = categories.id) WHERE records.user_id=? ORDER BY dateTime DESC", ROW_MAPPER, userId);
    }
}
