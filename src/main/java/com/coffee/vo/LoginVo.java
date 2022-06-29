package com.coffee.vo;


import com.coffee.validator.IsMobile;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginVo {

    @NotEmpty
    @IsMobile
    private String mobile;
    @NotEmpty
    @Length(min=32)
    private String password;
}
