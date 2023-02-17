package org.klozevitz.kameleoon_test.model.dao.daoDB;

public interface IDaoDB<E> {
    E findById(int id);
    E save(E e);
}
