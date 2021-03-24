package com.zhdanko.model;

import java.util.ArrayList;
import java.util.List;

public class Model<T>{
    private static Model instance = new Model();

    private final List<T> itemList;

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        itemList = new ArrayList<>();
    }

    public void addItem(T item) {
        itemList.add(item);
    }

    public List<T> listItem() {
        return itemList;
    }

    public void removeList(){
        itemList.clear();
    }

}
