package customer_service.service;

import customer_service.dtos.AddressResponseDTO;
import customer_service.dtos.CustomerResponseDTO;
import customer_service.mappers.AddressMapper;
import customer_service.model.Address;
import customer_service.repositories.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Transactional(readOnly = true)
    public List<AddressResponseDTO> findAll() {
        List<Address> result = addressRepository.findAll();

        if (result.isEmpty()) {
            return null;
        }

        // List para receber todos os endereços convertidos em DTO
        List<AddressResponseDTO> addressDTO = new ArrayList<>();

        for (Address address : result) {
            AddressResponseDTO dto = addressMapper.toDto(address);
            addressDTO.add(dto);
        }
        return addressDTO;
    }

    @Transactional(readOnly = true)
    public AddressResponseDTO findById(Long id){

        Optional<Address> result = addressRepository.findById(id);
        Address address;

        if (result.isEmpty()){
            return null;
        } else {
            address = result.get();
        }
        return addressMapper.toDto(address);

    }
}
