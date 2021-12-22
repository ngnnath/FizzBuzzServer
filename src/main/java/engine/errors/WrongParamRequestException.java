package engine.errors;

/**
 * WrongParamRequestException.
 */
public class WrongParamRequestException extends RuntimeException {

    public WrongParamRequestException() {
        super();
    }

    public WrongParamRequestException(final String aMessage) {
        super(aMessage);
    }

    public WrongParamRequestException(final String aMessage, final Throwable aCause) {
        super(aMessage, aCause);
    }

}
