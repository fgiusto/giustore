package at.giusto.giustore.core.commons.exception;

import static at.giusto.giustore.core.commons.enums.CoreCommonsError.VENDOR_ALREADY_EXISTS;

/**
 * The type Vendor already exists exception.
 */
public class VendorAlreadyExistsException extends CoreCommonsException{

    /**
     * Instantiates a new Vendor already exists exception.
     *
     * @param vendorName the vendor name
     */
    public VendorAlreadyExistsException(String vendorName) {
        super(VENDOR_ALREADY_EXISTS, vendorName);
    }
}
