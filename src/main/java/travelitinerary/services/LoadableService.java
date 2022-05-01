package travelitinerary.services;

import java.util.List;

public interface LoadableService<T> {
    List<T> save(List<T> items);
    void clear();
}
