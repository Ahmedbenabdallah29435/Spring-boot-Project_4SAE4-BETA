package com.example.techmasterpi.service;

import com.example.techmasterpi.domain.SellContract;
import com.example.techmasterpi.domain.SellerOffer;
import com.example.techmasterpi.domain.User;
import com.example.techmasterpi.repos.SellContractRepository;
import com.example.techmasterpi.repos.SellerOfferRepository;
import com.example.techmasterpi.repos.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.jws.soap.SOAPBinding;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class ContratService implements IContratService{
    @Autowired
    private SellContractRepository sellContractRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SellerOfferRepository sellerOfferRepository;
    @Autowired
    private Session session;



    @Override
    public void ajouterContrat(SellContract sellerContrat,int user_id,int sellid) throws MessagingException{
        User u=userRepository.findById(user_id).get();
        sellerContrat.setUserContractsale(u);
        SellerOffer v=sellerOfferRepository.findById(sellid).get();
        sellerContrat.setContractSell(v);
        sellContractRepository.save(sellerContrat);
        sendEmail(sellerContrat.getUserContractsale().getEmail(), "verification","Votre Contrat a ete creer ave succes");

    }

    @Override
    public void updateContrat(SellContract contract) {
        if (sellContractRepository.findById(contract.getContractsellid()).isPresent())
            sellContractRepository.save(contract);
        else
            System.out.println("doesnt exist");

    }

    @Override
    public void deleteContrat(int id) {
        sellContractRepository.deleteById(id);

    }

    @Override
    public SellContract get(int id) {
        return sellContractRepository.findById(id).get();
    }

    @Override
    public List<SellContract> findAllContrat() {
        return sellContractRepository.findAll();
    }

    public void sendEmail(String to, String subject, String text) throws MessagingException {
        Message message =new MimeMessage(session);
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(text);
        Transport.send(message);
    }
}
