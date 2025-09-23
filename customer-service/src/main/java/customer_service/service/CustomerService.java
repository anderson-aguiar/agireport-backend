package customer_service.service;

import customer_service.dtos.CustomerRequestDTO;
import customer_service.dtos.CustomerResponseDTO;
import customer_service.mappers.AddressMapper;
import customer_service.mappers.CustomerMapper;
import customer_service.model.Address;
import customer_service.model.Customer;
import customer_service.repositories.AddressRepository;
import customer_service.repositories.CustomerRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Transactional
    public CustomerResponseDTO save(CustomerRequestDTO requestDTO) {
        String zipCode = requestDTO.getAddress().getZipCode();
        int number = requestDTO.getAddress().getNumber();
        String street = requestDTO.getAddress().getStreet();

        Optional<Address> result = addressRepository.findByZipCodeAndNumberAndStreet(zipCode, number, street);

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

    @Transactional(readOnly = true)
    public CustomerResponseDTO findById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cliente não encontrado"));

        return customerMapper.toDto(customer);
    }
    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> findAll() {
        List<Customer> customers = customerRepository.findAll();

        return customers.stream().map(customer -> customerMapper.toDto(customer)).toList();

        //return customers.stream().map(customerMapper::toDto).toList();

    }
    @Transactional
    public CustomerResponseDTO update(Long id, CustomerRequestDTO requestDTO) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cliente não encontrado"));

        String zipCode = requestDTO.getAddress().getZipCode();
        int number = requestDTO.getAddress().getNumber();
        String street = requestDTO.getAddress().getStreet();

        Optional<Address> result = addressRepository.findByZipCodeAndNumberAndStreet(zipCode, number, street);

        Address address;

        if(result.isEmpty()){
            address = addressMapper.toEntity(requestDTO.getAddress());
        }else{
            address = result.get();
        }
        addressRepository.save(address);
        customer.setCpf(requestDTO.getCpf());
        customer.setName(requestDTO.getName());
        customer.setDateOfbirth(requestDTO.getDateOfbirth());
        customer.setIncome(requestDTO.getIncome());
        customer.setBankAccount(requestDTO.getBankAccount());
        customer.setGender(requestDTO.getGender());
        customer.setMaritalStatus(requestDTO.getMaritalStatus());
        customer.setJobTitle(requestDTO.getJobTitle());
        customer.setAddress(address);

        customerRepository.save(customer);
        return  customerMapper.toDto(customer);
    }
}
