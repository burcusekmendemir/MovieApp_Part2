package com.burcu.repository;

import com.burcu.dto.request.UserFindByEmailRequestDto;
import com.burcu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndPassword(String email, String password);

    //Kullanıcıları ismine göre sıralayan bir metot yazınız.
    List<User> findAllByOrderByName();

    //Kullanıcının girdiği bir isimle veritabanındaki bir ismin var olup olmadığını karşılaştırınız.
    Boolean existsByNameContainingIgnoreCase(String name);

    //Kullanıcının isim sorgulaması için girdiği harf veya kelimeye göre veritabanında sorgu yapan bir metot yazınız.
    List<User> findAllByNameContainingIgnoreCase(String query);

    Optional<User> findOptionalByEmailIgnoreCase(String email);

    List<User> findAllByEmailContainingIgnoreCase(String email);

    // Passwordunun uzunluğu belirlediğimiz sayıdan büyük olanları getiren sorguyu yazınız.
    // Native Query ve JPQL ile yazalım, Native Query'de @Param anotasyonunu kullanalım.

    // #1 Native Query -> tablonun ismine seslendiğimiz sorgular.
    @Query(value = "SELECT * FROM tbl_user as u WHERE length(u.password)>:p ", nativeQuery = true)
    List<User> findAllByPasswordLongerThan(@Param("p") Integer password);
    /*
    @Param anotasyonu password değişkenimize ("p") adını veriyor. Sonrasında query içerisinde p'e seslendiğimde aslında dışarıdan
    "password" ismi ile çağırdığım Integer değişkene sesleniyorum. @Param kullanımında parametrelerin sırası değil,query'de seslenmek için
    tanımladığımız @Param()'ın parantez içinde bulunan değişken ismi önemli oluyor.
    */

    // Native Query @Param'sız versiyon.
    @Query(value = "SELECT * FROM tbl_user as u WHERE length(u.password)>?1 ", nativeQuery = true)
    List<User> passwordLongerThanNoParam(Integer number);

    /*
    @Param yerine ?1, ?2, ?3... yaklaşımını kullanmaya karar  verirsem parametreleri aldığım sıra önem kazanıyor.
    ?1 -> İlk alınan parametrenin değerini query'e koyar.
    ?2 -> 2. alınan parametrenin değerini query'e koyar......
    Parametrenin sırasını bulup, ? + (param sıra no) şeklinde kullanırım.
     */


    // #2 JPQL -> DB'deki tablo ismine değil, Entity Class ismine (User) seslenilerek atılan sorgu.
    @Query("SELECT u FROM User AS u WHERE length(u.password)>?1 ")
    List<User> findAllByPasswordLongerThanJPQL(Integer password);

    //e-mailin sonu kullanıcının girdiği değerlere göre biten emailleri listeleyiniz.
    List<User> findAllByEmailEndingWith(String value);



}
