package ru.mycash.cash.service;

import ru.mycash.cash.dao.DAO;
import ru.mycash.cash.dao.DAOImpl;
import ru.mycash.cash.model.Record;

import java.util.List;

public class ServiseImpl implements Service{

    DAO dao = new DAOImpl();

    @Override
    public void create(Record record) {
        dao.create(record);
    }

    @Override
    public void update(Record record) {
dao.update(record);
    }

    @Override
    public void delete(Integer id) {
dao.delete(id);
    }

    @Override
    public Record get(Integer id) {
        return dao.get(id);
    }

    @Override
    public List<Record> getAll() {
        return dao.getAll();
    }
}
