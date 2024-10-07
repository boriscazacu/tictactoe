package org.example.helpers;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AppCache {
    private static AppCache INSTANCE;

    private String state;
    private final String X_STATE = "X";
    private final String O_STATE = "O";

    private AppCache() {}

    public static AppCache getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppCache();
        }
        return INSTANCE;
    }

    public String getState() {
        return state;
    }

    public void setXState() {
        this.state = X_STATE;
    }

    public void setOState() {
        this.state = O_STATE;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void randomState() {
        List<String> states = List.of(X_STATE, O_STATE);
        this.state = states.get(
                ThreadLocalRandom.current().nextInt(0, 2)
        );
    }
}
