package com.wolfred.gym.Controllers;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wolfred.gym.Dto.ClubRequestDTO;
import com.wolfred.gym.Dto.CreateUserAdminRequestDTO;
import com.wolfred.gym.Enums.ErrorResponse;
import com.wolfred.gym.Enums.UserRole;
import com.wolfred.gym.Response.ResponseUtil;
import com.wolfred.gym.Service.AuthService;
import com.wolfred.gym.Service.ClubService;
import com.wolfred.gym.Service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {
    @Autowired
    private AuthService service;

    @Autowired
    private ClubService clubService;

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<?> signUp(@RequestBody @Valid CreateUserAdminRequestDTO data, BindingResult result) {
        if(result.hasErrors()){
        return ResponseUtil.statusBadRequestResponse(result);
        }
        if(service.CreateUserAdmin(data) == null){
            return ResponseUtil.statusConflictResponse(ErrorResponse.UserWithProvidedEmailAlreadyExists.getMessage());
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

    @GetMapping("/customers/all")
    public ResponseEntity<?> listUsers() throws Exception {
        return ResponseUtil.statusOkResponse(userService.findByRoleNot(UserRole.ADMIN));
    }
    @GetMapping("/customers/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") long id) throws Exception {
        return ResponseUtil.statusOkResponse(userService.getUser(id));
    }

}
