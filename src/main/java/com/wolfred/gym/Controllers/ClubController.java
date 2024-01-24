package com.wolfred.gym.Controllers;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wolfred.gym.Dto.ClubResponseDTO;
import com.wolfred.gym.Response.ResponseUtil;
import com.wolfred.gym.Service.ClubService;



@RestController
@RequestMapping("/api/clubs")
public class ClubController {
    private ClubService clubService;

    private Map<String, Object> response = new HashMap<>();

    @Autowired
    public ClubController(ClubService clubService){
        this.clubService = clubService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> listClubs() {
        return ResponseUtil.statusOkResponse(clubService.findAll());
    }

    @GetMapping("/{id}/show")
    public ResponseEntity<?> show(@PathVariable("id") long id) {
        ClubResponseDTO clubResponseDTO = clubService.find(id);
        if(clubResponseDTO == null){
            return ResponseUtil.statusNotFoundEntityResponse("Club not found with id: "+id);
        }
        return ResponseUtil.statusOkResponse(clubResponseDTO);
    } 
    
}

