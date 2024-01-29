package com.burcu.dto.response;

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

public class RegisterResponseDto {
    String name;
    String surname;
    String email;
    EStatus eStatus;
    EUserType eUserType;
}
