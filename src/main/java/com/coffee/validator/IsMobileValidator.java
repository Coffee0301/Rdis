package com.coffee.validator;

import com.coffee.utils.ValidatorUtil;
import org.thymeleaf.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {

    private boolean required;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required=constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String mobile, ConstraintValidatorContext constraintValidatorContext) {
        if (required){
            return ValidatorUtil.isMobile(mobile);
        }
        else {
            if (StringUtils.isEmpty(mobile)){
                return  true;
            }else {
                return ValidatorUtil.isMobile(mobile);
            }
        }
    }
}
