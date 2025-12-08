public class GameModel {

    private int score;
    private int timeLeft;
    private boolean running;

    private static final int INITIAL_TIME = 30;

    public GameModel() {
        reset();
    }

    public void reset() {
        this.score = 0;
        this.timeLeft = INITIAL_TIME;
        this.running = true;
    }

    public void startGame() {
        this.score = 0;
        this.timeLeft = INITIAL_TIME;
        this.running = true;
    }

    public void stopGame() {
        this.running = false;
    }

    public void tick() {
        if (!running) return;
        if (timeLeft > 0) {
            timeLeft--;
            if (timeLeft == 0) {
                running = false;
            }
        }
    }

    public void registerHit() {
        if (running) {
            score++;
        }
    }

    public int getScore() {
        return score;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isGameOver() {
        return !running && timeLeft == 0;
    }
}
