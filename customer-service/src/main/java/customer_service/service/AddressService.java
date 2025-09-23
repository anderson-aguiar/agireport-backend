package customer_service.service;

import customer_service.dtos.AddressRequestDTO;
import customer_service.dtos.AddressResponseDTO;
import customer_service.mappers.AddressMapper;
import customer_service.model.Address;
import customer_service.repositories.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;

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

    @Transactional
    public AddressResponseDTO updateAddress(Long id, AddressRequestDTO requestDTO){

        Address address = addressRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Endereço não encontrado")
        );

        address.setCity(requestDTO.getCity());
        address.setNeighborhood(requestDTO.getNeighborhood());
        address.setNumber(requestDTO.getNumber());
        address.setState(requestDTO.getState());
        address.setStreet(requestDTO.getStreet());
        address.setZipCode(requestDTO.getZipCode());

        addressRepository.save(address);

        return addressMapper.toDto(address);

    }

}


