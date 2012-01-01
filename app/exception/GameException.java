package exception;

public class GameException extends RuntimeException {

    public GameException(String message) {
        super(message);
    }
    
    public GameException(String message, Exception source) {
        super(message, source);
    }
    
    public GameException(Exception source) {
        super(source);
    }
}
