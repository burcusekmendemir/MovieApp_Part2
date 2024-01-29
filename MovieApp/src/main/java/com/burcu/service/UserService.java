package com.burcu.service;

import com.burcu.dto.request.LoginRequestDto;
import com.burcu.dto.request.RegisterRequestDto;
import com.burcu.dto.request.UserExistsByNameRequestDto;
import com.burcu.dto.request.UserFindByEmailRequestDto;
import com.burcu.dto.response.LoginResponseDto;
import com.burcu.dto.response.RegisterResponseDto;
import com.burcu.entity.User;
import com.burcu.mapper.UserMapper;
import com.burcu.repository.UserRepository;
import com.burcu.utility.EStatus;
import com.burcu.utility.EUserType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements ICrudService<User, Long> {

    private final UserRepository userRepository;






    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public Iterable<User> saveAll(Iterable<User> t) {
        return null;
    }

    @Override
    public User deleteById(Long id) {
        Optional<User> user=userRepository.findById(id);
        if (user.isEmpty()){
            throw new NullPointerException("Böyle bir kullanıcı yok...");
        }else {
            user.get().setStatus(EStatus.INACTIVE);
            return userRepository.save(user.get());
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<User> user=userRepository.findById(id);
        if (user.isPresent()){
            return user;
        }else {
            throw new NullPointerException("Böyle bir kullanıcı yok...");
        }
    }

    @Override
    public List<User> findAll() {
        List<User> userList= userRepository.findAll();
        if (userList.isEmpty()){
            throw new NullPointerException("Liste boş..");
        }
        return userList;
    }
    public User register(String name, String surname, String email, String password, String rePassword){
        User user= User.builder()
                .name(name)
                .surname(surname)
                .password(password)
                .rePassword(rePassword)
                .build();
        if (!password.equals(rePassword) || password.isBlank()){ // " " -> isBlank=true " " isempty=false
            throw new RuntimeException("Şifreler uyuşmuyor, lütfen tekrar deneyiniz.");
        }

        return userRepository.save(user);

        /**
         * Exception -> checked ->compile eroor, derleme hatası
         * Runtime Exp -> unchecked -> runtime error, çalışmza zamanı hatası -> program çalışırken gerçekleşir
         */
    }



    public RegisterResponseDto registerDto(RegisterRequestDto dto) {
        User user=User.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .rePassword(dto.getRePassword())
                .build();
        if (!user.getPassword().equals(user.getRePassword()) || user.getPassword().isBlank()){ // " " -> isBlank=true " " isempty=false
            throw new RuntimeException("Şifreler uyuşmuyor.");
        }
        userRepository.save(user);
        //RequestDto ->User ->RespenseDto

        return RegisterResponseDto.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .eStatus(user.getStatus())//User'ın içini Request dto ile, Response'un içini oluşturduğumuz uer'ın değerleriyle doldurmalıyız.
                .build();

    }

    /**
     * # Bir login metodu yazalım. reopsitory'de email ve şifreye göre kullanıcı dönen bir metot yazılması gerekmektedir.
     * # DB'de bu kullanıcı varsa, sisteme giriş yapabiliyor olmalıyım.
     */
    public User login(String email, String password){
        Optional<User> user= userRepository.findByEmailAndPassword(email, password);
        if (user.isEmpty()){
            throw new RuntimeException("Kullanıcı bulunamadı...");
        }
        return user.get();
    }

    public LoginResponseDto loginDto(LoginRequestDto dto){
        Optional<User> user=userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if (user.isEmpty()){
            throw new RuntimeException("Email veya şifre hatalıdır..");
        }
       // User user1=user.get(); // diyerek de yapılabilir
        return LoginResponseDto.builder()
                .email(user.get().getEmail())
                .build();
    }

   public LoginResponseDto loginMapper(LoginRequestDto dto){
       Optional<User> optionalUser=userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());

       if (optionalUser.isEmpty()){
           throw new RuntimeException("Email veya şifre hatalıdır..");
       }
       return UserMapper.INSTANCE.fromUserToLoginResponseDto(optionalUser.get());
   }

    public RegisterResponseDto registerMapper2(RegisterRequestDto dto) {
        User user= UserMapper.INSTANCE.fromRegisterRequestDtoToUser(dto);

        if (!user.getPassword().equals(user.getRePassword()) || user.getPassword().isBlank()){
            throw new RuntimeException("Şifreler uyuşmuyor.");
        }
        return UserMapper.INSTANCE.fromUserToRegisterResponseDto(user);
    }

    /**
     * registerMapper() metodu için;
     * Aynı email ile ikinci kez kayıt işlemi yapılmamalıdır. Bu durumu service katmanında yönetiniz.
     * ba.admin@email.com şeklinde kayıt işlemi gerçekleştiren kullanıcının status'u Active,
     * type'ı ise Admin olmalıdır. Bu işlevselliği kazandırınız
     */
    @Transactional // işlemlerin atomik (tek bir işlem olarak tamamlandığını) ve tutarlı bir şekilde çalışmasına yardımcı olur.
    public RegisterResponseDto registerMapper(RegisterRequestDto dto) {
        User user= UserMapper.INSTANCE.fromRegisterRequestDtoToUser(dto);

        if (user.getEmail().equalsIgnoreCase("ba.admin@email.com")) {
            user.setStatus(EStatus.ACTIVE);
            user.setUserType(EUserType.ADMIN);
        } else if (!userRepository.findAllByEmailContainingIgnoreCase(user.getEmail()).isEmpty()) {
            throw new RuntimeException("Bu email ile bir kullanıcı zaten kayıtlı.");
        }
        if (!user.getPassword().equals(user.getRePassword()) || user.getPassword().isBlank()) {
            throw new RuntimeException("Sifreler uyuşmuyor.");
        }

        userRepository.save(user);
        return UserMapper.INSTANCE.fromUserToRegisterResponseDto(user);
    }




    public List<User> findAllByOrderByName() {
      return userRepository.findAllByOrderByName();
    }

    public Boolean existsByNameContainingIgnoreCase(UserExistsByNameRequestDto dto) {
      return userRepository.existsByNameContainingIgnoreCase(dto.getName());
    }


    public List<User> findAllByNameContainingIgnoreCase(String name) {
        return userRepository.findAllByNameContainingIgnoreCase(name);
    }


    public Optional<User> findOptionalByEmailIgnoreCase(UserFindByEmailRequestDto dto) {
      return userRepository.findOptionalByEmailIgnoreCase(dto.getEmail());
    }

    public List<User> findAllByPasswordLongerThan(Integer password) {
        return userRepository.findAllByPasswordLongerThan(password);
    }
    public List<User> passwordLongerThanNoParam(Integer number) {
        return userRepository.passwordLongerThanNoParam(number);
    }

    public List<User> findAllByPasswordLongerThanJPQL(Integer password) {
        return userRepository.findAllByPasswordLongerThanJPQL(password);
    }

    public List<User> findAllByEmailEndingWith(String value) {
        return userRepository.findAllByEmailEndingWith(value);
    }



}
