package com.barmej.notesapp.data.database.model;

import java.util.List;

public class Items {
    private  int type;
    private Object object;

    public Items(int type, Object object) {
        this.type = type;
        this.object = object;
    }

    public int getType() {
        return type;
    }

    public Object getObject() {
        return object;
    }
}
