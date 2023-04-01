package com.example.techmasterpi.service;

import com.example.techmasterpi.domain.Furniture;
import com.example.techmasterpi.domain.Relocation;
import com.example.techmasterpi.domain.User;
import com.example.techmasterpi.model.FurnitureDTO;
import com.example.techmasterpi.model.RelocationDTO;
import com.example.techmasterpi.repos.FurnitureRepository;
import com.example.techmasterpi.repos.RelocationRepository;
import com.example.techmasterpi.repos.UserRepository;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;
import com.example.techmasterpi.util.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RelocationService implements IFurnitureService{
    private RelocationRepository relocationRepository;
    private UserRepository userRepository;
    private final FurnitureRepository furnitureRepository;

    public RelocationService( RelocationRepository relocationRepository,
                              UserRepository userRepository,
                              FurnitureRepository furnitureRepository) {
        this.relocationRepository = relocationRepository;
        this.userRepository = userRepository;
        this.furnitureRepository = furnitureRepository;
    }

    public List<RelocationDTO> findAll() {
        final List<Relocation> relocations = relocationRepository.findAll(Sort.by("relocationid"));
        return relocations.stream()
                .map((relocation) -> mapToDTO(relocation, new RelocationDTO()))
                .collect(Collectors.toList());
    }

    public RelocationDTO get(Integer relocationid) {
        return relocationRepository.findById(relocationid)
                .map(relocation -> mapToDTO(relocation, new RelocationDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(RelocationDTO relocationDTO, int userid) {
    User u =userRepository.findById(userid).orElse(new User());
         Relocation relocation = new Relocation();
        mapToEntity(relocationDTO, relocation);
        relocation.setUserRelocation(u);
        return relocationRepository.save(relocation).getRelocationid();
    }

    public void update(Integer relocationid, RelocationDTO relocationDTO) {
         Relocation relocation = relocationRepository.findById(relocationid)
                .orElseThrow(NotFoundException::new);
        mapToEntity(relocationDTO, relocation);
        relocationRepository.save(relocation);
    }

    public void delete(final Integer relocationid) {
        relocationRepository.deleteById(relocationid);
    }

    private RelocationDTO mapToDTO( Relocation relocation,  RelocationDTO relocationDTO) {
        relocationDTO.setRelocationid(relocation.getRelocationid());
        relocationDTO.setRelocationdate(relocation.getRelocationdate());
        relocationDTO.setLocationdep(relocation.getLocationdep());
        relocationDTO.setLocationarr(relocation.getLocationarr());
        relocationDTO.setUserRelocation(relocation.getUserRelocation() == null ? null : relocation.getUserRelocation().getUserid());
        return relocationDTO;
    }

    private Relocation mapToEntity(RelocationDTO relocationDTO, Relocation relocation) {
        relocation.setRelocationdate(relocationDTO.getRelocationdate());
        relocation.setLocationdep(relocationDTO.getLocationdep());
        relocation.setLocationarr(relocationDTO.getLocationarr());
        final User userRelocation = relocationDTO.getUserRelocation() == null ? null : userRepository.findById(relocationDTO.getUserRelocation())
                .orElseThrow(() -> new NotFoundException("userRelocation not found"));
        relocation.setUserRelocation(userRelocation);
        return relocation;
    }

}
