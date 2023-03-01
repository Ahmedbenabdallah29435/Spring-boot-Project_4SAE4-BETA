package com.example.techmasterpi.service;

import com.example.techmasterpi.domain.SellContract;
import com.example.techmasterpi.domain.SellerOffer;

import javax.mail.MessagingException;
import java.util.List;

public interface IContratService {
    public void ajouterContrat (SellContract sellerContrat,int user_id,int sellid)throws MessagingException;



    public void updateContrat (SellContract contract);
    void deleteContrat(int id);
    public SellContract get(int id);

    public List<SellContract> findAllContrat();
}
