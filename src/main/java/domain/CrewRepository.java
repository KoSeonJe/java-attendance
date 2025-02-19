package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CrewRepository {
    private static final CrewRepository INSTANCE = new CrewRepository();

    private final Map<String, Crew> crews = new HashMap<>();

    private CrewRepository() {
    }

    public static CrewRepository getInstance() {
        return INSTANCE;
    }

    public void save(Crew crew) {
        crews.put(crew.getName(), crew);
    }

    public Crew findByName(String name) {
        if (crews.containsKey(name)) {
            return crews.get(name);
        }
        throw new IllegalArgumentException("해당 이름의 출석 정보가 없습니다.");
    }

    public Optional<Crew> findOptionalByName(String name) {
        return Optional.ofNullable(crews.get(name));
    }


    public List<Crew> findAll() {
        return crews.values().stream()
                .toList();
    }

    public void clear() {
        crews.clear();
    }
}
