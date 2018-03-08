package ru.mycash.cash.dao;

import ru.mycash.cash.model.Record;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DAOImpl implements DAO {

    Map<Integer,Record> repository = Repository.getRepository();

    @Override
    public void create(Record record) {
        repository.put(record.getId().get(), record);
    }

    @Override
    public void update(Record record) {
repository.replace(record.getId().get(),record);
    }

    @Override
    public void delete(Integer id) {
repository.remove(id);
    }

    @Override
    public Record get(Integer id) {
        return repository.get(id);
    }

    @Override
    public List<Record> getAll() {
        return new ArrayList<>(repository.values());
    }
}
