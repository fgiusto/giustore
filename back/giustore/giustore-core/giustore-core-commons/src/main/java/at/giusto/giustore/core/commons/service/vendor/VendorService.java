package at.giusto.giustore.core.commons.service.vendor;

import at.giusto.giustore.core.commons.entity.Owner;
import at.giusto.giustore.core.commons.exception.VendorAlreadyExistsException;
import at.giusto.giustore.core.commons.exception.VendorNotFoundException;
import at.giusto.giustore.core.commons.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type Vendor service.
 */
@Service
public class VendorService {

    private final VendorRepository vendorRepository;

    /**
     * Instantiates a new Vendor service.
     *
     * @param vendorRepository the vendor repository
     */
    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    /**
     * Gets vendor.
     *
     * @param vendorId the vendor id
     * @return the vendor
     */
    public Owner getVendor(int vendorId) {
        return findVendor(vendorId).orElseThrow(() -> new VendorNotFoundException(vendorId));
    }

    /**
     * Add vendor vendor.
     *
     * @param name the name
     * @return the vendor
     */
    public Owner addVendor(String name) {
        // search name
        if (isVendorNameExists(name)) {
            throw new VendorAlreadyExistsException(name);
        }

        return save(buildVendor(name));
    }

    /**
     * Delete vendor.
     *
     * @param vendorId the vendor id
     */
    public void deleteVendor(int vendorId) {
        Owner found = getVendor(vendorId);
        vendorRepository.delete(found);
    }

    /**
     * Update vendor vendor.
     *
     * @param vendorId the vendor id
     * @param newName  the new name
     * @return the vendor
     */
    public Owner updateVendor(int vendorId, String newName) {
        Owner found = getVendor(vendorId);
        found.setName(newName);
        return save(found);
    }

    private boolean isVendorNameExists(String name) {
        return findVendor(name).isPresent();
    }

    private Optional<Owner> findVendor(String name) {
        return vendorRepository.findByName(name);
    }

    private Owner buildVendor(String name) {
        Owner owner = new Owner();

        owner.setName(name);

        return owner;
    }

    private Optional<Owner> findVendor(int vendorId) {
        return vendorRepository.findById(vendorId);
    }

    private Owner save(Owner owner) {
        return vendorRepository.save(owner);
    }
}
