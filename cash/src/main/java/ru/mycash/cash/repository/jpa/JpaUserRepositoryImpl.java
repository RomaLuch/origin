package ru.mycash.cash.repository.jpa;

import org.springframework.stereotype.Repository;
import ru.mycash.cash.model.User;
import ru.mycash.cash.repository.UserRepository;

import java.util.List;

@Repository
public class JpaUserRepositoryImpl implements UserRepository {
    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
