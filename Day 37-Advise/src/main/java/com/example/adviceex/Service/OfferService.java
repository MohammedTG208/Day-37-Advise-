package com.example.adviceex.Service;

import com.example.adviceex.Api.ApiException;
import com.example.adviceex.Model.Offer;
import com.example.adviceex.Model.Product;
import com.example.adviceex.Model.User;
import com.example.adviceex.Repository.OfferRepository;
import com.example.adviceex.Repository.ProductRepository;
import com.example.adviceex.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferService {
    private final OfferRepository offerRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<Offer> getAllOffers() {
        if (offerRepository.findAll().isEmpty()) {
            throw new ApiException("No offers found in Database");
        }
        return offerRepository.findAll();
    }

    //1
    public void addNewOffer(Offer offer) {
        //check product exists
        if (productRepository.findProductById(offer.getProductId()) == null) {
            throw new ApiException("Product not found");
        } else {
            //check product status
            if (productRepository.findProductById(offer.getProductId()).getStatus().equalsIgnoreCase("active")) {
                User user = userRepository.findUserById(offer.getUserId());
                //check user have enough balance or not
                if (user.getBalance() > productRepository.findProductById(offer.getProductId()).getStartPrice() && user.getBalance() > productRepository.findProductById(offer.getProductId()).getUserPrice()) {
                   //check user offer is bigger than start price and last user offer
                        if (offer.getPrice() > productRepository.findProductById(offer.getProductId()).getUserPrice() && offer.getPrice() > productRepository.findProductById(offer.getProductId()).getStartPrice()) {
                            Product product = productRepository.findProductById(offer.getProductId());
                            product.setUser_id(offer.getUserId());
                            product.setUserPrice(offer.getPrice());
                            user.setBalance(offer.getPrice() - user.getBalance());
                            userRepository.save(user);
                            productRepository.save(product);
                            offerRepository.save(offer);
                        } else {
                            throw new ApiException("Price is less than another offer price");
                        }
                } else {
                    throw new ApiException("Product is not active");
                }
            }else {
                throw new ApiException("your balance is not enough");
            }
        }
    }

    public void deleteOffer(Integer id) {
        if (offerRepository.findOfferById(id)==null){
            throw new ApiException("No offers found by this ID");
        }
        offerRepository.deleteById(id);
    }

    public void updateOffer(Offer offer,Integer id) {
        if (offerRepository.findOfferById(id)==null){
            throw new ApiException("No offers found by this ID");
        }
        Offer oldOffer = offerRepository.findOfferById(id);
        oldOffer.setProductId(offer.getProductId());
        oldOffer.setUserId(offer.getUserId());
        offerRepository.save(oldOffer);
    }

}
