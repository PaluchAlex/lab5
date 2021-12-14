package ro.ubbcluj.map.service;

import ro.ubbcluj.map.domain.Entity;
import ro.ubbcluj.map.domain.validators.Validator;
import ro.ubbcluj.map.repository.Repository;

public abstract class AbstractService<ID, E extends Entity<ID>> {
    protected Repository<ID, E> repo;

    public AbstractService(Repository<ID, E> repo) {
        this.repo = repo;
    }

    public void addEntity(E e) { repo.save(e); }
    public void updateEntity(E e) { repo.update(e); }
    public void deleteEntity(ID id) { repo.delete(id); }
    public E get(ID id) { return repo.findOne(id); }
    public Iterable<E> getAll(){ return repo.findAll(); }
}
