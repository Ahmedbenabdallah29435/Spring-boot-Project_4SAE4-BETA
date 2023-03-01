package com.example.techmasterpi.service;

import com.example.techmasterpi.domain.OffreFavorit;
import com.example.techmasterpi.domain.SellerOffer;
import com.example.techmasterpi.domain.User;
import com.example.techmasterpi.repos.OfferFavoriteRepository;
import com.example.techmasterpi.repos.SellerOfferRepository;
import com.example.techmasterpi.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;



@Service
public class SellerService implements ISellerService {
    @Autowired
    private SellerOfferRepository sellerOfferRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OfferFavoriteRepository offerFavoriteRepository;

    @Override
    public SellerOffer ajouterOffer(SellerOffer sellerOffer, int user_id) {
        User u = userRepository.findById(user_id).get();
        sellerOffer.setUser(u);
        sellerOfferRepository.save(sellerOffer);

        return sellerOffer;
    }


    @Override
    public void updateOffer(SellerOffer c) {

        if (sellerOfferRepository.findById(c.getSellid()).isPresent())
            sellerOfferRepository.save(c);
        else
            System.out.println("doesnt exist");

    }

    @Override
    public void deleteOffer(int idc) {

        sellerOfferRepository.deleteById(idc);

    }

    public SellerOffer get(int id) {
        return sellerOfferRepository.findById(id).get();
    }

    @Override
    public List<SellerOffer> findAllOffer() {

        return sellerOfferRepository.findAll();

    }


    public List<SellerOffer> offer() {

        return sellerOfferRepository.findAll();

    }

    @Override
    public void markAsSold(Long id) {
        SellerOffer property = sellerOfferRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new EntityNotFoundException("Offer not found with id: " + id));
        property.setSold(true);
        sellerOfferRepository.save(property);
    }

    public SellerOffer saveoffer(SellerOffer sellerOffer, int id) {
        User u = userRepository.findById(id).get();
        sellerOffer.setUser(u);
        return sellerOfferRepository.save(sellerOffer);
    }

    public static String saveImage(MultipartFile image) throws IOException {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        Path path = Paths.get("uploads");
        Files.createDirectories(path);
        try (InputStream inputStream = image.getInputStream()) {
            Path filePath = path.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            return filePath.toString();
        } catch (IOException e) {
            throw new IOException("Could not save file " + fileName, e);
        }
    }

        @Override
    public void markOfferAsFavorite(Long id) {
        Optional<SellerOffer> optionalOffer = Optional.of(sellerOfferRepository.findById(Math.toIntExact(id)).get());
        if (optionalOffer.isPresent()) {
            SellerOffer offer = optionalOffer.get();


            OffreFavorit favorite = new OffreFavorit();
            favorite.setSellerOffer(offer);
            favorite.setTimestamp(LocalDateTime.now());


        offerFavoriteRepository.save(favorite);

            offer.setFavorite(true);
            sellerOfferRepository.save(offer);
        }
    }
//    public SellerOffer MarkOfferAsFavorite(SellerOffer offer) {
//        SellerOffer favoriteOffer = sellerOfferRepository.save(offer);
//
//        OffreFavorit favorite = new OffreFavorit();
//        favorite.setSellerOffer(favoriteOffer);
//        favorite.setTimestamp(LocalDateTime.now());
//        favorite.setAction("updated");
//        offerFavoriteRepository.save(favorite);
//
//        return favoriteOffer;
//    }
//
//    @Override
//    public List<SellerOffer> findByHistorique() {
//        return sellerOfferRepository.findByHistorique();
//    }


//    public List<SellerOffer> getFavoriteOffers() {
//        return sellerOfferRepository.findByFavoriteTrue();
//    }
//public List<OffreFavorit> getAllOffresFavorites() {
//    return OfferFavoriteRepository.findAll();
//}
@Override
public List<OffreFavorit> findAllOfferFavorit() {

    return offerFavoriteRepository.findAll();

}

    }



