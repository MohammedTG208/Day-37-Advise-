package com.example.adviceex.Controller;

import com.example.adviceex.Model.Owner;
import com.example.adviceex.Service.OwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/owner")
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerService ownerService;

    @GetMapping("/getall/{adminId}")
    public ResponseEntity getAllOwners(@PathVariable Integer adminId) {
        return ResponseEntity.ok(ownerService.getAllOwners(adminId));
    }

    @PostMapping("/add")
    public ResponseEntity addOwner(@Valid @RequestBody Owner owner) {
            ownerService.addNewOwner(owner);
            return ResponseEntity.status(200).body("owner added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateOwner(@PathVariable Integer id,@Valid @RequestBody Owner owner) {
            ownerService.updateOwner(owner,id);
            return ResponseEntity.status(200).body("owner updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOwner(@PathVariable Integer id) {
        ownerService.deleteOwner(id);
        return ResponseEntity.status(200).body("owner deleted successfully");
    }

    @GetMapping("/get/owner/product/{pro}")
    public ResponseEntity getOwner(@PathVariable Integer pro) {
        return ResponseEntity.status(200).body(ownerService.getAllOwnerProduct(pro));
    }
}
