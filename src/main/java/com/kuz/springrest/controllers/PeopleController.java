package com.kuz.springrest.controllers;


import com.kuz.springrest.model.Person;
import com.kuz.springrest.services.PeopleService;
import com.kuz.springrest.util.PersonErrorResponse;
import com.kuz.springrest.util.PersonNotCreatedException;
import com.kuz.springrest.util.PersonNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;


    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping()
    public List<Person> getPeople() {
        return peopleService.findAll();//Jackson автоматически форматирует обекты в JSON

    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable("id") int id)
    {
        return  peopleService.findOne(id);
    }


    /*Указанной анотацией мы помечаем метод который ловит исключение и который возвращает необходимый объект*/
    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e)
    {
            PersonErrorResponse response = new PersonErrorResponse(
                    "Person with  this id wasn't found!",
                    System.currentTimeMillis()
            );


            //body response and status handle
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); //NOT_FOUND -404
    }

    /*Можно возвращать любой объект jackson его смапин всеравно
    * например Person
    * @RequestBody @Valid Person person -> jackson смапин данные пришедшие с пост запроса в объект Person
    * если он будет валидный(т.к. отмечен Valid)*/
    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Person person, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            StringBuilder errorMsg = new StringBuilder();

            var fieldErrors = bindingResult.getFieldErrors();

            fieldErrors.stream()
                    .forEach(fieldError ->
                    {
                        errorMsg.append(fieldError.getField()); //поле где ошибка
                        errorMsg.append(" - ");
                        errorMsg.append(fieldError.getDefaultMessage()); //описание ошибки
                        errorMsg.append(";"); // разделитель
                    });

                throw new PersonNotCreatedException(errorMsg.toString());
        }


        peopleService.save(person);


        //В данном случае если мы не хотим зодавать отд. объект то мы возращаем сообщение что все ОК!!!
        //тело будет бустым и статус 200!
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotCreatedException e)
    {
        PersonErrorResponse response = new PersonErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );


        //body response and status handle
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }





}
