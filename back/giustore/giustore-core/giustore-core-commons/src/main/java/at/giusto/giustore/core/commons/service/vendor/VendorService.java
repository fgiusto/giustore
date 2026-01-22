package at.giusto.giustore.core.commons.service.vendor;

import at.giusto.giustore.core.commons.entity.Vendor;
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
    public Vendor getVendor(int vendorId) {
        return findVendor(vendorId).orElseThrow(() -> new VendorNotFoundException(vendorId));
    }

    /**
     * Add vendor vendor.
     *
     * @param name the name
     * @return the vendor
     */
    public Vendor addVendor(String name) {
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
        Vendor found = getVendor(vendorId);
        vendorRepository.delete(found);
    }

    /**
     * Update vendor vendor.
     *
     * @param vendorId the vendor id
     * @param newName  the new name
     * @return the vendor
     */
    public Vendor updateVendor(int vendorId, String newName) {
        Vendor found = getVendor(vendorId);
        found.setName(newName);
        return save(found);
    }

    private boolean isVendorNameExists(String name) {
        return findVendor(name).isPresent();
    }

    private Optional<Vendor> findVendor(String name) {
        return vendorRepository.findByName(name);
    }

    private Vendor buildVendor(String name) {
        Vendor vendor = new Vendor();

        vendor.setName(name);

        return vendor;
    }

    private Optional<Vendor> findVendor(int vendorId) {
        return vendorRepository.findById(vendorId);
    }

    private Vendor save(Vendor vendor) {
        return vendorRepository.save(vendor);
    }
}
