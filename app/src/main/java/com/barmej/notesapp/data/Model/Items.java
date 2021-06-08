package com.barmej.notesapp.data.Model;

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
