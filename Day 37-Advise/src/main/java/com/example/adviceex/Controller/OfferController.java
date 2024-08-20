package com.example.adviceex.Controller;

import com.example.adviceex.Model.Offer;
import com.example.adviceex.Service.OfferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/offer")
public class OfferController {
    private final OfferService offerService;

    @GetMapping("/getall")
    public ResponseEntity getAllOffers() {
        return ResponseEntity.ok(offerService.getAllOffers());
    }

    @PostMapping("/add")
    public ResponseEntity addOffer(@Valid @RequestBody Offer offer) {
            offerService.addNewOffer(offer);
            return ResponseEntity.status(200).body("offer added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateOffer(@PathVariable Integer id,@Valid @RequestBody Offer offer ) {
            offerService.updateOffer(offer,id);
            return ResponseEntity.status(200).body("offer updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOffer(@PathVariable Integer id) {
        offerService.deleteOffer(id);
        return ResponseEntity.status(200).body("offer deleted successfully");
    }
}
