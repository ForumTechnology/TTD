package com.example.security.model.Blog;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class BlogDTO implements Validator {
    @NotBlank(message = "Not empty")
    @Size(min = 3, message = ">3 characters")
    @Pattern(regexp = "^[a-z ]*$", message = "only contain character")
    private String name;

    @Size(min = 3, message = ">3 characters")
    private String type;
    private Long category;

    public BlogDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
