package com.example.digishopapi.controllers;

import com.example.digishopapi.models.User;
import com.example.digishopapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users/")
public class UserController {
   @Autowired
    UserService userService;
   @GetMapping("{id}")
   public ResponseEntity<User> getUserById(@PathVariable("id") String id){
       return new ResponseEntity<User>(userService.getUserById(id),HttpStatus.OK);
   }
   @PutMapping("{id}")
   public ResponseEntity<User> updateUserById(@PathVariable("id") String id,@RequestBody User user){
       return new ResponseEntity<User>(userService.updateUserById(id,user),HttpStatus.OK);
   }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") String id){
         userService.deleteUserById(id);
         return new ResponseEntity<String>("user deleted successfully", HttpStatus.OK);
    }
    @GetMapping("all")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String pageNumber){
         List<User> users=userService.getAllUsers(pageNumber);
         return new ResponseEntity<List<User>>(users,users.size()==0?HttpStatus.NOT_FOUND: HttpStatus.OK);
    }


    @GetMapping("all/{page}")
    public ResponseEntity<List<User>> getAllPaginatedUsers(@PathVariable("page") int page){
         List<User> users=userService.getPageableShops(page);
         return new ResponseEntity<List<User>>(users,users.size()==0?HttpStatus.NOT_FOUND: HttpStatus.OK);
    }
}
