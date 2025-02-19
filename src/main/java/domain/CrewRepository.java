package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrewRepository {
    private final Map<String, Crew> crews = new HashMap<>();

    public void save(Crew crew) {
        crews.put(crew.getName(), crew);
    }

    public Crew findByName(String name) {
        if (crews.containsKey(name)) {
            return crews.get(name);
        }
        throw new IllegalArgumentException("해당 이름의 출석 정보가 없습니다.");
    }

    public List<Crew> findAll() {
        return crews.values().stream()
                .toList();
    }
}
