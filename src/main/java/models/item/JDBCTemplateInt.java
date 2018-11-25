package models.item;

import java.util.List;

/**
 * JDBCTemlapeInt
 */
public interface JDBCTemplateInt <T> {
    List<T> findAll();
}