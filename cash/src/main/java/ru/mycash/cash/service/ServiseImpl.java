package ru.mycash.cash.service;

import ru.mycash.cash.repository.Repository;
import ru.mycash.cash.model.Record;
import ru.mycash.cash.repository.RepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ServiseImpl implements Service{

    Repository repository = new RepositoryImpl();


    @Override
    public void save(Record record) {
        repository.save(record);
    }

    @Override
    public void delete(Integer id) {
repository.delete(id);
    }

    @Override
    public Record get(Integer id) {
        return repository.get(id);
    }

    @Override
    public List<Record> getAll() {
        return new ArrayList<>(repository.getAll());
    }
}
