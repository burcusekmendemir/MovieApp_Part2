package com.burcu.mapper;

import com.burcu.dto.request.RegisterRequestDto;
import com.burcu.dto.request.UserExistsByNameRequestDto;
import com.burcu.dto.response.LoginResponseDto;
import com.burcu.dto.response.RegisterResponseDto;
import com.burcu.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    //bu sınıfta bir hata verilirse run la geçiyor


    /**
     * Arka planda build sırasında kullandığımız kendi yaptığımız
     * buildleri field isimleri aynı ise mapper işleyip sonuç dönüyor.
     */
    UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);

    LoginResponseDto fromUserToLoginResponseDto(final User user); //final yazmamızın sebebi entitynin içeriği korunur.

    User fromRegisterRequestDtoToUser(final RegisterRequestDto dto);
    RegisterResponseDto fromUserToRegisterResponseDto(final User user);

    UserExistsByNameRequestDto fromUserToUserExistsByNameRequestDto(final User user);
}
