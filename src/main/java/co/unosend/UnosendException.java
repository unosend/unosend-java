package co.unosend;

public class UnosendException extends Exception {
    private final int statusCode;

    public UnosendException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() { return statusCode; }
}
