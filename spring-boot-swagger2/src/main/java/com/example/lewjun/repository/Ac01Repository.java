package com.example.lewjun.repository;

import com.example.lewjun.domain.Ac01;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class Ac01Repository {
    private final Map<Integer, Ac01> repository = Collections.synchronizedMap(new HashMap<>());

    public List<Ac01> getList() {
        return new ArrayList<>(repository.values());
    }

    public void save(final Ac01 ac01) {
        final int nextAac001 = repository.isEmpty() ? 1 : Collections.max(repository.keySet()) + 1;
        ac01.setAac001(nextAac001);
        repository.put(nextAac001, ac01);
    }

    public Ac01 get(final int aac001) {
        return repository.get(aac001);
    }

    public void delete(final int aac001) {
        repository.remove(aac001);
    }

    public void update(final Ac01 ac01) {
        final int aac001 = ac01.getAac001();
        if (repository.containsKey(aac001)) {
            repository.put(aac001, ac01);
        } else {
            throw new IllegalArgumentException("更新失败");
        }
    }
}
