package at.giusto.giustore.core.commons.exception;

import static at.giusto.giustore.core.commons.enums.CoreCommonsError.VENDOR_NOT_FOUND;

/**
 * The type Vendor already exists exception.
 */
public class VendorNotFoundException extends CoreCommonsException {

    /**
     * Instantiates a new Vendor not found exception.
     *
     * @param vendorId the vendor id
     */
    public VendorNotFoundException(int vendorId) {
        super(VENDOR_NOT_FOUND, String.valueOf(vendorId));
    }
}
