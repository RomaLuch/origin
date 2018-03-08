package ru.mycash.cash.service;

import ru.mycash.cash.model.Record;

import java.util.List;

public interface Service {
    void create(Record record);
    void update(Record record);
    void delete(Integer id);
    Record get(Integer id);
    List<Record> getAll();
}
