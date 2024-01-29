package com.burcu.controller;

import com.burcu.dto.request.LoginRequestDto;
import com.burcu.dto.request.RegisterRequestDto;
import com.burcu.dto.request.UserExistsByNameRequestDto;
import com.burcu.dto.request.UserFindByEmailRequestDto;
import com.burcu.dto.response.LoginResponseDto;
import com.burcu.dto.response.RegisterResponseDto;
import com.burcu.entity.User;
import com.burcu.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;



    @GetMapping("/find-by-id")
    public ResponseEntity<Optional<User>> findById(Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }
    @DeleteMapping("/delete")
    public ResponseEntity<User> deleteById(Long id){
        return ResponseEntity.ok(userService.deleteById(id));
    }

    //basic register
    @PostMapping("/register")
    public ResponseEntity<User> register(String name, String surname, String email, String password, String rePassword) {
        return ResponseEntity.ok(userService.register(name, surname, email, password, rePassword));
        //basicLogin
    }
    @PostMapping("/login")
    public ResponseEntity<User> login (String email, String password){
        return ResponseEntity.ok(userService.login(email, password));
    }

        //dto register
     @PostMapping("/register-dto")
     public ResponseEntity<RegisterResponseDto> registerDto (@RequestBody RegisterRequestDto dto) {
        return ResponseEntity.ok(userService.registerDto(dto));
    }

     @PostMapping("/login-dto")
    public ResponseEntity<LoginResponseDto> loginDto(@RequestBody LoginRequestDto dto){
        return ResponseEntity.ok(userService.loginDto(dto));
    }

    @PostMapping("/register-mapper")
    public ResponseEntity<RegisterResponseDto> registerMapper(@RequestBody RegisterRequestDto dto){
        return ResponseEntity.ok(userService.registerMapper(dto));
    }


    @PostMapping("/login-mapper")
    public ResponseEntity<LoginResponseDto> loginMapper(@RequestBody LoginRequestDto dto){
        return ResponseEntity.ok(userService.loginMapper(dto));
    }


    //Kullanıcıları ismine göre sıralayan bir metot yazınız.
    @GetMapping("/find-all-order-by-name")
    public ResponseEntity<List<User>> findAllByOrderByName(){
        return ResponseEntity.ok(userService.findAllByOrderByName());
    }

    //Kullanıcının girdiği bir isimle veritabanındaki bir ismin var olup olmadığını karşılaştırınız.
    @GetMapping("/exists-by-name")
    public ResponseEntity<Boolean> existsByNameContainingIgnoreCase(@RequestBody UserExistsByNameRequestDto dto){
        return ResponseEntity.ok(userService.existsByNameContainingIgnoreCase(dto));
    }

    //Kullanıcının isim sorgulaması için girdiği harf veya kelimeye göre veritabanında sorgu yapan bir metot yazınız.
    @GetMapping("/find-by-name-containing-ignore-case")
    public ResponseEntity<List<User>> findByNameContainingIgnoreCase(@RequestParam (value = "name", required = false) String name) {
        return ResponseEntity.ok(userService.findAllByNameContainingIgnoreCase(name));
    }

    //Kullanıcının girdiği email'e göre veritabanında sorgu yapan bir metot yazınız.
    @GetMapping("/find-by-email")
    public ResponseEntity<Optional<User>> findOptionalByEmailIgnoreCase(@RequestBody UserFindByEmailRequestDto dto) {
        return ResponseEntity.ok(userService.findOptionalByEmailIgnoreCase(dto));
    }

    /**
     * passwordunun uzunluğu belirlediğimiz sayıdan büyük olanları getiren sorguyu yazınız.
     * Native Query ve JPQL ile yazalım, Native Query'de @Param anotasyonunu kullanalım.
     * emailin sonu kullanıcının girdiği değerlere göre biten emailleri listeleyiniz.
     */

    @GetMapping("/find-by-password")
    public ResponseEntity<List<User>> findAllByPasswordLongerThan(Integer password){
        return ResponseEntity.ok(userService.findAllByPasswordLongerThan(password));
    }


    //Ekstra -> paramsız çözüm
    @GetMapping("/password-longer-than-no-param")
    public ResponseEntity<List<User>> passwordLongerThanNoParam(Integer number) {
        return ResponseEntity.ok(userService.passwordLongerThanNoParam(number));
    }

    @GetMapping("/fin-by-password-longer-than-jpql")
    public ResponseEntity<List<User>> findAllByPasswordLongerThanJPQL(Integer password) {
        return ResponseEntity.ok(userService.findAllByPasswordLongerThanJPQL(password));
    }

    @GetMapping("/find-all-by-email-ending-with")
    public ResponseEntity<List<User>> findAllByEmailEndingWith(String value) {
        return ResponseEntity.ok(userService.findAllByEmailEndingWith(value));
    }









}
