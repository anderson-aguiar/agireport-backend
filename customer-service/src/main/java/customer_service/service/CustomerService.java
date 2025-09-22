package customer_service.service;

import customer_service.dtos.CustomerRequestDTO;
import customer_service.dtos.CustomerResponseDTO;
import customer_service.mappers.AddressMapper;
import customer_service.mappers.CustomerMapper;
import customer_service.model.Address;
import customer_service.model.Customer;
import customer_service.repositories.AddressRepository;
import customer_service.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerResponseDTO save(CustomerRequestDTO requestDTO) {
        String zipCode = requestDTO.getAddress().getZipCode();
        int number = requestDTO.getAddress().getNumber();

        Optional<Address> result = addressRepository.findByZipCodeAndNumber(zipCode, number);

        Address address;

        if(result.isEmpty()){
            address = addressMapper.toEntity(requestDTO.getAddress());
            addressRepository.save(address);
        }else{
            address = result.get();
        }
        Customer customer = customerMapper.toEntity(requestDTO, address);
        customerRepository.save(customer);

        return customerMapper.toDto(customer);

    }
}
