package com.example.lewjun.repository;

import com.example.lewjun.domain.Ab01;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class Ab01RepositoryImpl implements Ab01Repository {
    private final Map<Integer, Ab01> repository = Collections.synchronizedMap(new HashMap<>());

    @Override
    public List<Ab01> getList() {
        return new ArrayList<>(repository.values());
    }

    @Override
    public void save(final Ab01 ab01) {
        final int nextAab001 = repository.isEmpty() ? 1 : Collections.max(repository.keySet()) + 1;

        ab01.setAab001(nextAab001);

        repository.put(nextAab001, ab01);
    }

    @Override
    public Ab01 get(final int aab001) {
        return repository.get(aab001);
    }

    @Override
    public void delete(final int aab001) {
        repository.remove(aab001);
    }

    @Override
    public void update(final Ab01 ab01) {
        repository.put(ab01.getAab001(), ab01);
    }
}
