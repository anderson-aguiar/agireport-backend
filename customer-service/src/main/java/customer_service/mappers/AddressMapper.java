package customer_service.mappers;

import customer_service.dtos.AddressRequestDTO;
import customer_service.model.Address;

public class AddressMapper {

    public Address toEntity(AddressRequestDTO dto)
    {
        if (dto == null) return null; // Retorna nulo se o DTO for nulo

        Address address = new Address();
        address.setStreet(dto.getStreet());
        address.setCity(dto.getCity());
        address.setNumber(dto.getNumber());
        address.setNeighborhood(dto.getNeighborhood());
        address.setZipCode(dto.getZipCode());
        address.setState(dto.getState());
        return address;

    }
}
