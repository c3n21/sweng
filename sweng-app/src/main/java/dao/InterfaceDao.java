package dao;

import java.util.List;

import dao.exceptions.DaoGenericException;

/**
 * InterfaceDao
 */
public interface InterfaceDao<T> {
List<T> get(String[] params) throws DaoGenericException;

void save(T t) throws DaoGenericException;

void update(T t, String[] params) throws DaoGenericException;

void delete(T t) throws DaoGenericException;
}
