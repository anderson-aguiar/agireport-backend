package customer_service.controllers;

import customer_service.dtos.AddressRequestDTO;
import customer_service.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @ResponseBody AddressRequestDTO requestDTO){

        addressService.updateAddress(id, requestDTO);
        return ResponseEntity.noContent().build();
    }
}
