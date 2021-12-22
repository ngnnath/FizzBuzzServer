package engine.errors;

/**
 * Error FizzbuzzParamException
 */
public class FizzbuzzParamException extends RuntimeException {

    public FizzbuzzParamException() {
        super();
    }

    public FizzbuzzParamException(final String aMessage) {
        super(aMessage);
    }

    public FizzbuzzParamException(final String aMessage, final Throwable aCause) {
        super(aMessage, aCause);
    }

}
