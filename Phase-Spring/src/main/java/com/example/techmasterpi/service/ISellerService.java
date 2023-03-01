package com.example.techmasterpi.service;

import com.example.techmasterpi.domain.OffreFavorit;
import com.example.techmasterpi.domain.SellerOffer;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ISellerService {


    public SellerOffer ajouterOffer (SellerOffer sellerOffer, int user_id);

    public SellerOffer saveoffer(SellerOffer sellerOffer,int id);

    public void updateOffer (SellerOffer s);
    void deleteOffer(int id);
    public SellerOffer get(int id);

    public List<SellerOffer> findAllOffer();
    public void markAsSold(Long id);
  void markOfferAsFavorite(Long id);
//    public List<SellerOffer> getFavoriteOffers();
//    public SellerOffer MarkOfferAsFavorite(SellerOffer offer);
//    List<SellerOffer> findByHistorique();
//
public List<OffreFavorit> findAllOfferFavorit();







}
