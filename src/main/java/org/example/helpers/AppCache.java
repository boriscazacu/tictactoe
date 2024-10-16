package org.example.helpers;

import org.example.board.OElement;
import org.example.board.XElement;
import org.example.interfaces.Player;
import org.example.player.Bot;
import org.example.player.User;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AppCache {
    private static AppCache INSTANCE;

    private final String X_STATE = "X";
    private final String O_STATE = "O";

    private Player firstPlayer;
    private Player secondPlayer;
    private boolean first = true;

    private AppCache() {}

    public static AppCache getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppCache();
        }
        return INSTANCE;
    }

    public void setXState() {
        this.firstPlayer = new User(new XElement());
        this.secondPlayer = new Bot(new OElement());
    }

    public void setOState() {
        this.firstPlayer = new User(new OElement());
        this.secondPlayer = new Bot(new XElement());
    }

    public void randomState() {
        String state = getRandomState();
        if (X_STATE.equals(state)) {
            setXState();
        } else {
            setOState();
        }
    }

    private String getRandomState() {
        List<String> states = List.of(X_STATE, O_STATE);
        return states.get(
                ThreadLocalRandom.current().nextInt(0, 2)
        );
    }

    public void userToUserInit(String u1, String u2) {
        String state = getRandomState();
        if (X_STATE.equals(state)) {
            this.firstPlayer = new User(new XElement()).setUserName(u1);
            this.secondPlayer = new User(new OElement()).setUserName(u2);
        } else {
            this.firstPlayer = new User(new OElement()).setUserName(u1);
            this.secondPlayer = new User(new XElement()).setUserName(u2);
        }
    }

    public boolean isPlayingWithBot() {
        return this.secondPlayer instanceof Bot;
    }

    public Player getPlayerToMove() {
        if (first) {
            first =  false;
            return this.firstPlayer;
        }
        first = true;
        return this.secondPlayer;
    }

    public Player getNextMove() {
        if (first) {
            return this.firstPlayer;
        }
        return this.secondPlayer;
    }

    public Player getFirstPlayer() {
        if (firstPlayer == null) {
            throw new RuntimeException("User is not initialized !!");
        }
        return firstPlayer;
    }

    public Player getSecondPlayer() {
        if (secondPlayer == null) {
            throw new RuntimeException("Second user is not initialized !!");
        }
        return secondPlayer;
    }
}
