package dao;

import java.util.Optional;

/**
 * 
 */
public interface InterfaceDao<T> {

/**
 * Restituisce, se presente, un oggetto T dal database.
 *
 * @param params eventuali parametri aggiuntivi
 */
Optional<T> get(Object[] params);

/**
 * Inserisce `t` nel database.
 */
void save(T t);

/**
 * Aggiorna, se presente, un oggetto `t` nel database.
 *
 * @param params eventuali parametri aggiuntivi
 */
void update(T t, String[] params);

/**
 * Elimina, se presente, un oggetto `t` nel database.
 */
void delete(T t);
}
