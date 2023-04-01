package com.example.techmasterpi.service;

import com.example.techmasterpi.domain.Delivery;
import com.example.techmasterpi.domain.Relocation;
import com.example.techmasterpi.domain.User;
import com.example.techmasterpi.model.DeliveryDTO;
import com.example.techmasterpi.repos.DeliveryRepository;
import com.example.techmasterpi.repos.RelocationRepository;
import com.example.techmasterpi.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import com.example.techmasterpi.util.NotFoundException;


@Service
public class DeliveryService implements  IDeliveryService {
@Autowired
private DeliveryRepository deliveryRepository ;

    private UserRepository userRepository;
    private RelocationRepository relocationRepository;
public DeliveryService(DeliveryRepository deliveryRepository,UserRepository userRepository,RelocationRepository relocationRepository){
    this.deliveryRepository = deliveryRepository;
    this.userRepository = userRepository;
    this.relocationRepository = relocationRepository;
}
    public List<DeliveryDTO> findAll() {
        final List<Delivery> deliverys = deliveryRepository.findAll(Sort.by("deliveryid"));
        return deliverys.stream()
                .map((delivery) -> mapToDTO(delivery, new DeliveryDTO()))
                .collect(Collectors.toList());
    }

    public DeliveryDTO get(Integer deliveryid) {
        return deliveryRepository.findById(deliveryid)
                .map(delivery -> mapToDTO(delivery, new DeliveryDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(DeliveryDTO deliveryDTO,int idUser , int idRelocation) {
        final Delivery delivery = new Delivery();
        mapToEntity(deliveryDTO, delivery);
        User u = userRepository.findById(idUser).orElse(new User());
        Relocation r = relocationRepository.findById(idRelocation).orElse(new Relocation());
        delivery.setUserDelevery(u);
        delivery.setRelocationDelivery(r);
        return deliveryRepository.save(delivery).getDeliveryid();
    }

    public void update(Integer deliveryid, DeliveryDTO deliveryDTO) {
        final Delivery delivery = deliveryRepository.findById(deliveryid)
                .orElseThrow(NotFoundException::new);
        mapToEntity(deliveryDTO, delivery);
        deliveryRepository.save(delivery);
    }
    public void affecterUserAndRelocation(){


    }

    public void delete( Integer deliveryid) {
        deliveryRepository.deleteById(deliveryid);
    }

    private DeliveryDTO mapToDTO(Delivery delivery, DeliveryDTO deliveryDTO) {
        deliveryDTO.setDeliveryid(delivery.getDeliveryid());
        deliveryDTO.setTotaleprice(delivery.getTotaleprice());
        deliveryDTO.setServicedetail(delivery.getServicedetail());
        deliveryDTO.setTrack(delivery.getTrack());
        deliveryDTO.setState(delivery.getState());
        deliveryDTO.setUserDelevery(delivery.getUserDelevery() == null ? null : delivery.getUserDelevery().getUserid());
        deliveryDTO.setRelocationDelivery(delivery.getRelocationDelivery() == null ? null : delivery.getRelocationDelivery().getRelocationid());
        return deliveryDTO;
    }

    private Delivery mapToEntity(DeliveryDTO deliveryDTO,  Delivery delivery) {
        delivery.setTotaleprice(deliveryDTO.getTotaleprice());
        delivery.setServicedetail(deliveryDTO.getServicedetail());
        delivery.setTrack(deliveryDTO.getTrack());
        delivery.setState(deliveryDTO.getState());
        final User userDelevery = deliveryDTO.getUserDelevery() == null ? null : userRepository.findById(deliveryDTO.getUserDelevery())
                .orElseThrow(() -> new NotFoundException("userDelevery not found"));
        delivery.setUserDelevery(userDelevery);
        final Relocation relocationDelivery = deliveryDTO.getRelocationDelivery() == null ? null : relocationRepository.findById(deliveryDTO.getRelocationDelivery())
                .orElseThrow(() -> new NotFoundException("relocationDelivery not found"));
        delivery.setRelocationDelivery(relocationDelivery);
        return delivery;
    }

}
