package com.coffee.vo;


import com.coffee.validator.IsMobile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
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
