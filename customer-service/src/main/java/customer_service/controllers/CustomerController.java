package customer_service.controllers;

import customer_service.dtos.CustomerRequestDTO;
import customer_service.dtos.CustomerResponseDTO;
import customer_service.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController { //POST, GET. PUT. DELETE

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> create(@RequestBody CustomerRequestDTO requestDTO){
        CustomerResponseDTO response = customerService.save(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



}
