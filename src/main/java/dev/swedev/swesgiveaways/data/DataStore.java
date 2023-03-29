package dev.swedev.swesgiveaways.data;

import dev.swedev.swesgiveaways.Plugin;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public abstract class DataStore {

    @Getter
    private final String name;

    public DataStore(String name) {
        this.name = name;
    }

    @Getter
    private static final List<DataStore> methods = new ArrayList<>();

    public static DataStore getMethod(String name) {
        for (DataStore store : getMethods()) {
            if (store.getName().equalsIgnoreCase(name)) {
                return store;
            }
        }
        return null;
    }

    public abstract void load();

    public abstract void save();

    public abstract void remove(int index);
}
