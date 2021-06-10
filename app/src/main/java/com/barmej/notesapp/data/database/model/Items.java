package com.barmej.notesapp.data.database.model;

public class Items {
    private  int type;
    private Object object;

    public Items( Object object,int type) {
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
