package ru.mycash.cash.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mycash.cash.model.User;
import ru.mycash.cash.repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return repository.remove(id)!=null;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);

        if(user.isNew())
        {
            user.setId(counter.incrementAndGet());
            repository.put(user.getId(),user);
            return user;
        }
        return repository.computeIfPresent(user.getId(),(id, oldUser)->user);
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        return repository.values()
                .stream()
                .sorted(Comparator.comparing(User::getName))
                .collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return getAll()
                .stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findAny()
                .orElse(null);
    }
}
