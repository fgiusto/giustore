package at.giusto.giustore.core.commons.exception;

/**
 * The type Core commons exception.
 */
public class CoreCommonsException extends RuntimeException{

    private final String errorDetail;

    /**
     * Instantiates a new Core commons exception.
     *
     * @param error       the error
     * @param errorDetail the error detail
     */
    public CoreCommonsException(CoreCommonsErrorInterface error, String errorDetail) {
        super(error.name());
        this.errorDetail = errorDetail;
    }

    /**
     * Gets error detail.
     *
     * @return the error detail
     */
    public String getErrorDetail() {
        return errorDetail;
    }
}
