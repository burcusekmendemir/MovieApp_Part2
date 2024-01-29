package com.burcu.dto.request;

import com.burcu.utility.EStatus;
import com.burcu.utility.EUserType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {

    String name;
    String surname;
    String email;
    String password;
    String rePassword;

}
