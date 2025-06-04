package dataaccess;

import java.lang.Exception;
import java.util.List;
import exception.DAOException;
public interface RepositoryDAO<T> {
// Добавление сущности в таблицу базы данных
// возвращает ID добавленного должности
public Long insert(T о) throws Exception;
// Редактирование cущности
public void update(T o) throws Exception;
// Удаление сущности
public void delete(Long Id) throws Exception;
// Поиск сущности по Id
public T findById(Long Id) throws Exception;
// Получение списка сущностей
public List<T> findAll() throws Exception;
}
