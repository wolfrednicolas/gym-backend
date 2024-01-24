package com.wolfred.gym.Controllers;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wolfred.gym.Dto.ClubRequestDTO;
import com.wolfred.gym.Dto.CreateUserAdminRequestDTO;
import com.wolfred.gym.Response.ResponseUtil;
import com.wolfred.gym.Service.AuthService;
import com.wolfred.gym.Service.ClubService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AuthService service;

    @Autowired
    private ClubService clubService;

    @PostMapping("/createUser")
    public ResponseEntity<?> signUp(@RequestBody @Valid CreateUserAdminRequestDTO data, BindingResult result) {
        if(result.hasErrors()){
        return ResponseUtil.statusBadRequestResponse(result);
        }
        if(service.CreateUserAdmin(data) == null){
        return ResponseUtil.statusConflictResponse("User with the provided email already exists.");
        }
        return ResponseUtil.statusCreatedResponse();
    }

    @PostMapping("/clubs/new")
    public ResponseEntity<?> create(@Valid @RequestBody ClubRequestDTO clubDto
                                , BindingResult result) throws Exception {
        if(result.hasErrors()){
            return ResponseUtil.statusBadRequestResponse(result);
        }
        this.clubService.create(clubDto);
        return ResponseUtil.statusCreatedResponse();
    }

    @PutMapping("/clubs/{id}/edit")
    public ResponseEntity<?> update(@PathVariable("id") long id
                        , @Valid @RequestBody ClubRequestDTO clubRequestDTO
                        , BindingResult result) throws Exception {
        if(result.hasErrors()){
            return ResponseUtil.statusUnprocessableEntityResponse(result);
        }
        return ResponseEntity.ok(clubService.update(id, clubRequestDTO));
    }

}
