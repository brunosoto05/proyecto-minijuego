import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameModel model = new GameModel();
                GameView view = new GameView();
                new GameController(model, view);
            }
        });
    }
}
