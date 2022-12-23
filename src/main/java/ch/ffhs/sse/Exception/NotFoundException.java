package ch.ffhs.sse.Exception;

public class NotFoundException extends Exception {

    /**
     * Returns a message as an Exception message
     * @param message
     */
    public NotFoundException(String message) {
        super(message);
    }

}
