package com.kuz.springrest.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
public class Person {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "name")
    @NotBlank(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and character")
    private String name;

    @Column(name = "age")
    @Min(value = 1, message = "The age must be at least 1 year")
    private int age;

    @Column(name = "email")
    @Email
    @NotBlank(message = "email should not be empty")
    private String email;


    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
