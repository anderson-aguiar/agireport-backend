package customer_service.mappers;

import customer_service.dtos.AddressResponseDTO;
import customer_service.dtos.CustomerRequestDTO;
import customer_service.dtos.CustomerResponseDTO;
import customer_service.model.Address;
import customer_service.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    @Autowired
    private AddressMapper addressMapper;


    public Customer toEntity(CustomerRequestDTO dto) {

        if (dto == null) return null;

        Customer customer = new Customer();
        customer.setCpf(dto.getCpf());
        customer.setName(dto.getName());
        customer.setDateOfbirth(dto.getDateOfbirth());
        customer.setIncome(dto.getIncome());
        customer.setBankAccount(dto.getBankAccount());
        customer.setGender(dto.getGender());
        customer.setMaritalStatus(dto.getMaritalStatus());
        customer.setJobTitle(dto.getJobTitle());

        Address address = addressMapper.toEntity(dto.getAddress());
        customer.setAddress(address);

        return customer;

    }
    public CustomerResponseDTO toDto(Customer entity){
        if(entity == null) return null;

        CustomerResponseDTO dto = new CustomerResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCpf(entity.getCpf());
        dto.setDateOfbirth(entity.getDateOfbirth());
        dto.setIncome(entity.getIncome());
        dto.setBankAccount(entity.getBankAccount());
        dto.setGender(entity.getGender());
        dto.setMaritalStatus(entity.getMaritalStatus());
        dto.setJobTitle(entity.getJobTitle());

        AddressResponseDTO addressResponseDTO = addressMapper.toDto(entity.getAddress());
        dto.setAddressResponseDTO(addressResponseDTO);
        return dto;
    }

}
