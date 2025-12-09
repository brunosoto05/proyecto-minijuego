import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController {

    private GameModel model;
    private GameView view;

    private Timer gameTimer;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;

        initTimer();
        connectViewEvents();
        updateViewFromModel();
    }

    private void initTimer() {
        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onGameTick();
            }
        });
        gameTimer.setRepeats(true);
    }

    private void connectViewEvents() {
        view.addStartButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onStartGame();
            }
        });

        view.addTargetButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onTargetClicked();
            }
        });
    }

    private void onStartGame() {
        model.startGame();
        updateViewFromModel();
        view.setGameRunning(true);
        view.setStatus("Jugando...");
        gameTimer.start();
    }

    private void onTargetClicked() {
        if (!model.isRunning()) {
            return;
        }
        model.registerHit();
        view.setScore(model.getScore());
        view.moveTargetRandom();
    }

    private void onGameTick() {
        model.tick();
        view.setTimeLeft(model.getTimeLeft());

        if (!model.isRunning()) {
            gameTimer.stop();
        }
    }

    private void updateViewFromModel() {
        view.setScore(model.getScore());
        view.setTimeLeft(model.getTimeLeft());
        if (model.isRunning()) {
            view.setStatus("Jugando...");
            view.setGameRunning(true);
        } else {
            view.setStatus("Detenido");
            view.setGameRunning(false);
        }
    }
}
