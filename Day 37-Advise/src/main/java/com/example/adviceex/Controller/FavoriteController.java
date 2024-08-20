package com.example.adviceex.Controller;

import com.example.adviceex.Model.Favorite;
import com.example.adviceex.Service.FavoriteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/favorite")
public class FavoriteController {
    private final FavoriteService favoriteService;

    @GetMapping("/getall")
    public ResponseEntity getAllFavorites() {
        return ResponseEntity.ok(favoriteService.getAllFavorites());
    }

    @PostMapping("/add")
    public ResponseEntity addFavorite(@Valid @RequestBody Favorite favorite) {
            favoriteService.addNewFavorite(favorite);
            return ResponseEntity.status(200).body("Favorite added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateFavorite(@PathVariable Integer id,@Valid @RequestBody Favorite favorite) {
        favoriteService.updateFavorite(id, favorite);
        return ResponseEntity.status(200).body("Favorite updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteFavorite(@PathVariable Integer id) {
        favoriteService.deleteFavorite(id);
        return ResponseEntity.status(200).body("Favorite deleted successfully");
    }

    @GetMapping("/get/similar/user/favorite/{userId}")
    public ResponseEntity getUserFavorite(@PathVariable Integer userId){
        return ResponseEntity.status(200).body(favoriteService.getSimilarFavoriteProductsForUser(userId));
    }
}
