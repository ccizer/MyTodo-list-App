package com.ccizer.webapps.todolist.domain.validator;

import com.ccizer.webapps.todolist.domain.User;
import com.ccizer.webapps.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * <h1>Register Validator</h1>
 *
 * User register validation definitions
 *
 * @author  Can Çizer
 * @version 1.0
 * @since   2017-01-04
 */
@Component
public class RegisterValidator implements Validator {
    @Autowired
    private UserService userService;

    @Autowired
    public RegisterValidator() {
    }
    
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(User.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User form = (User) o;
        validateUsername(errors, form);
        validateEmail(errors, form);
    }

    /**
     *
     * Checks the user exists or not.
     *
     * @param errors
     * @param form
     */
    private void validateUsername(Errors errors, User form) {
        if (userService.getUserByUsername(form.getUsername()) != null) {
            errors.reject("username.exists", "User with this username already exists");
        }
    }

    /**
     *
     * Checks the email is correct or not
     *
     * @param errors
     * @param form
     */
    private void validateEmail(Errors errors, User form) {
        if (userService.getUserByEmail(form.getEmail()) != null) {
            errors.reject("email.exists", "User with this email already exists");
        }else {

            try {
                InternetAddress internetAddress = new InternetAddress(form.getEmail());
                internetAddress.validate();
            } catch (AddressException e) {
                errors.reject("email.wrong", "Mail address is not correct");
            }

        }
    }
}
