package customer_service.controllers;

import customer_service.dtos.AddressResponseDTO;
import customer_service.dtos.CustomerResponseDTO;
import customer_service.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        addressService.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping
    public ResponseEntity<List<AddressResponseDTO>> findAll() {

        List<AddressResponseDTO> response = addressService.findAll();

        if (response == null || response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }
}
