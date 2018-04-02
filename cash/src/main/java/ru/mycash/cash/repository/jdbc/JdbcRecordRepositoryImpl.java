package ru.mycash.cash.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.mycash.cash.model.Record;
import ru.mycash.cash.repository.RecordRepository;
import ru.mycash.cash.util.TimeUtil;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Created by RLuchinsky on 02.04.2018.
 */
@Repository
public class JdbcRecordRepositoryImpl implements RecordRepository {

    private static final RowMapper<Record> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Record.class);

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
        return null;
    }

    @Override
    public boolean delete(Integer id, Integer userId) {
        return false;
    }

    @Override
    public Record get(Integer id, Integer userId) {
        return null;
    }

    @Override
    public Collection<Record> getAll(Integer userId) {

        RowMapper rowMapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                System.out.println("**********************"+i+"************************");
                System.out.println(resultSet.getString(1));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString(3));
                System.out.println(resultSet.getString(4));
                System.out.println(resultSet.getString(5));
                System.out.println(resultSet.getString(6));
                Record record = new Record(resultSet.getInt(1),
                        TimeUtil.parseTmeStamp(resultSet.getString(2)),
                        resultSet.getString(3),
/*                        resultSet.getInt(6),*/
                        resultSet.getInt(4));

                return record;
            }
        };

        return jdbcTemplate.query(
                "SELECT * FROM records WHERE user_id=? ORDER BY dateTime DESC", rowMapper,userId);
    }
}
