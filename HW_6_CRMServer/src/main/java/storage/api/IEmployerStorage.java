package storage.api;

import model.Employer;

import java.util.List;

public interface IEmployerStorage {
    long add(Employer employer);
    Employer get(Long id);
    List<Employer> getAll();
    List<Employer> getLimit(long limit, long offset);
    long getCount();
}
