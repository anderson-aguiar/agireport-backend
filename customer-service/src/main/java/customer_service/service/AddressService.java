package customer_service.service;

import customer_service.model.Address;
import customer_service.repositories.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Transactional
    public void deleteById(Long id) {
        Address address = addressRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Endereço não encontrado"));

        if (address.getCustomers().isEmpty()) {
            addressRepository.delete(address);
        } else {
            throw new IllegalStateException("Endereço relacionado a outros clientes");
        }
    }


}
