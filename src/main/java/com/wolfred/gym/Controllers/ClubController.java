package com.wolfred.gym.Controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;


import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wolfred.gym.Dto.ClubDto;
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
    public List<ClubDto> listClubs() {
        return clubService.findAll();
    }

    @PostMapping("/new")
    public ResponseEntity<?> create(@Valid @RequestBody ClubDto clubDto
                                , BindingResult result) throws Exception {
        if(result.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
        }
        this.clubService.create(clubDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping("/{id}/show")
    public ResponseEntity<?> show(@PathVariable("id") long id) {
        ClubDto clubDto = clubService.find(id);
        if(clubDto == null){
            return ResponseUtil.statusNotFoundEntityResponse("Club not found with id: "+id);
        }
        return ResponseUtil.statusOkResponse(clubDto);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<?> update(@PathVariable("id") long id
                        , @Valid @RequestBody ClubDto clubDto
                        , BindingResult result) throws Exception {
        if(result.hasErrors()){
            return ResponseUtil.statusUnprocessableEntityResponse(result);
        }
        return ResponseEntity.ok(clubService.update(id, clubDto));
    }
    
    
}

