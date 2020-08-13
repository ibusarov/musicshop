package myproject.repository;

import myproject.model.entity.Contact;
import myproject.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact,String> {

    Optional<Contact> findByEmail(String email);
}
