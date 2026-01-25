package at.giusto.giustore.core.commons.repository;

import at.giusto.giustore.core.commons.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface Vendor repository.
 */
@Repository
public interface VendorRepository extends JpaRepository<Owner, Integer> {

    /**
     * Find by name optional.
     *
     * @param name the name
     * @return the optional
     */
    Optional<Owner> findByName(String name);
}
