package customer_service.mappers;

import customer_service.dtos.CustomerRequestDTO;
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

}
