package com.example.techmasterpi.Controller;

import com.example.techmasterpi.domain.OffreFavorit;
import com.example.techmasterpi.domain.SellContract;
import com.example.techmasterpi.domain.SellerOffer;
import com.example.techmasterpi.domain.User;
import com.example.techmasterpi.repos.OfferFavoriteRepository;
import com.example.techmasterpi.repos.SellerOfferRepository;
import com.example.techmasterpi.service.IContratService;
import com.example.techmasterpi.service.ISellerService;
import com.example.techmasterpi.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(value = "/api/seller")
public class SellerController {
    @Autowired
    private ISellerService sellerService;
    @Autowired
    private IContratService contratService;
    @Autowired
    private OfferFavoriteRepository offerFavoriteRepository;
    @Autowired
    private ISellerService favoritOffer;


    @PostMapping("addOffer/{user_id}")
    public void addOffer(@RequestBody SellerOffer sellerOffer,@PathVariable("user_id") int user_id)
    {
        sellerService.ajouterOffer(sellerOffer,user_id);
    }
    @PutMapping("updateOffer")
    void updateOffer(@RequestBody SellerOffer c)
    {

        sellerService.updateOffer(c);
    }
    @DeleteMapping("deleteOffer/{idc}")
    void deleteOffer(@PathVariable("idc") int idc)
    {

        sellerService.deleteOffer(idc);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SellerOffer> getOfferById(@PathVariable int id) {
        SellerOffer Offer = sellerService.get(id);
        if (Offer == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(Offer);
        }
    }
        @GetMapping("offer")
        public List<SellerOffer> findAllOffer(){
            return sellerService.findAllOffer();
        }
    @PostMapping("addContrat/{user_id}/{sell_id}")
    public void addContrat(@RequestBody SellContract contract,@PathVariable("user_id") int user_id,@PathVariable("sell_id") int sellid)throws MessagingException
    {
        contratService.ajouterContrat(contract,user_id,sellid);
    }
    @PutMapping("updateContrat")
    void updateContract(@RequestBody SellContract c)
    {
        contratService.updateContrat(c);
    }
    @DeleteMapping("deleteContrat/{idc}")
    void deleteContract(@PathVariable("idc") int id)
    {
        contratService.deleteContrat(id);
    }
    @GetMapping("/{idc}")
    public ResponseEntity<SellContract> getContratById(@PathVariable int id) {
        SellContract Contrat = contratService.get(id);
        if (Contrat == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(Contrat);
        }
    }
    @GetMapping("Contract")
    public List<SellContract> findAllContrat(){
        return contratService.findAllContrat();
    }
    @PutMapping("sold/{id}")
    public ResponseEntity<?> markAsSold(@PathVariable Long id) {
        sellerService.markAsSold(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping("uploadImage/{user_id}")
    public ResponseEntity<SellerOffer> createUser(@RequestParam("description") String descriptipon,@RequestParam("title") String title,@RequestParam(value = "image", required = false) MultipartFile image,@PathVariable("user_id") int user_id) throws IOException {
        SellerOffer newOffer = new SellerOffer(descriptipon,title);


        if (image != null) {
            String imagePath = SellerService.saveImage(image);
            newOffer.setPicture(imagePath);
        }
        SellerOffer savedOffer = sellerService.saveoffer(newOffer,user_id);
        return ResponseEntity.ok(savedOffer);
    }
   @PutMapping("favorite/{id}")
    public ResponseEntity<?> markOfferAsFavorite(@PathVariable Long id) {
        sellerService.markOfferAsFavorite(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("favorite")
    public List<OffreFavorit> findAllOfferfavorit(){
        return favoritOffer.findAllOfferFavorit();
    }
//    @GetMapping("/offers/favorites")
//    public ResponseEntity<List<SellerOffer>> getFavoriteOffers() {
//        List<SellerOffer> favoriteOffers = sellerService.getFavoriteOffers();
//        return ResponseEntity.ok(favoriteOffers);
//    }

//    @GetMapping
//    public List<OffreFavorit> getOfferHistory(@PathVariable int Id) {
//        SellerOffer offer = new SellerOffer();
//        offer.setSellid(Id);
//
//        ExampleMatcher matcher = ExampleMatcher.matching()
//                .withMatcher("offer", ExampleMatcher.GenericPropertyMatchers.exact().ignoreCase());
//
//        Example<OffreFavorit> example = Example.of(new OffreFavorit(null, offer,null, null), matcher);
//        Sort sort = Sort.by(Sort.Direction.DESC, "timestamp");
//        return offerFavoriteRepository.findAll(example, sort);
//    }
//
//
//    @GetMapping("/getDataCenters")
//    public List<SellerOffer> getDataCenter(){
//        return sellerService.findByHistorique();
//    }

}







