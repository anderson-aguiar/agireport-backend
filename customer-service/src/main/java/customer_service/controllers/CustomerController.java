package customer_service.controllers;

import customer_service.dtos.CustomerRequestDTO;
import customer_service.dtos.CustomerResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    public ResponseEntity<CustomerResponseDTO> create(@RequestBody CustomerRequestDTO requestDTO){

    }

}
