package com.kc.demo.service.impl;

import com.kc.demo.service.ItemRepository;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemRepositoryImpl.
 */
public class ItemRepositoryImpl implements ItemRepository{

    /** The row elements. */
    private List<List<String>> rowElements = null;
    
    /** The size of rows. */
    private int sizeOfRows = 0;

    /**
     * Instantiates a new item repository impl.
     *
     * @param rowElements the row elements
     */
    public ItemRepositoryImpl(List<List<String>> rowElements) {
        this.rowElements = rowElements;
        if (null != rowElements && !rowElements.isEmpty()) {
            this.sizeOfRows = rowElements.size();
        }
    }

    /* (non-Javadoc)
     * @see com.kc.demo.service.ItemRepository#getAllElements()
     */
    @Override
    public List<List<String>> getAllElements() {
        if (null != rowElements && !rowElements.isEmpty()) {
            this.sizeOfRows = rowElements.size();
            return rowElements;
        }else {
            throw new RuntimeException("Collection provided after reading file is Empty");
        }
    }

    /* (non-Javadoc)
     * @see com.kc.demo.service.ItemRepository#getRowById(int)
     */
    @Override
    public List<String> getRowById(int index) {
        if (index < sizeOfRows) {
            return getAllElements().get(index);
        } else {
            throw new RuntimeException(String.format("Specified Row Index %s not exists in " +
                    "collection provided",index));
        }
    }

    /* (non-Javadoc)
     * @see com.kc.demo.service.ItemRepository#getSpecificElementFromRow(int, int)
     */
    @Override
    public String getSpecificElementFromRow(int rowIndex, int itemindex) {
        List<String> rowElements = getRowById(rowIndex);
        if (itemindex < rowElements.size()) {
            return getRowById(rowIndex).get(itemindex);
        }else {
            throw new RuntimeException(String.format("Specified Element in index %s is " +
                    "not exist in row %s",itemindex,rowIndex));
        }

    }
}
