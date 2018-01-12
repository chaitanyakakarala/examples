package com.kc.demo.service;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface ItemRepository.
 */
public interface ItemRepository {

    /**
     * Gets the all elements.
     *
     * @return the all elements
     */
    List<List<String>> getAllElements();

    /**
     * Gets the row by id.
     *
     * @param index the index
     * @return the row by id
     */
    List<String> getRowById(int index) ;

    /**
     * Gets the specific element from row.
     *
     * @param rowIndex the row index
     * @param itemindex the itemindex
     * @return the specific element from row
     */
    String getSpecificElementFromRow(int rowIndex,int itemindex);
}
