package com.kuz.springrest.controllers;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

//Ясли у нас все методы будут возвращать данные то можно использовать @RestController весто @Controller


@RestController
@RequestMapping("/api")
public class FirstRestController {


/*Данная анотация указыает на то что при обращении на api/sayHello будет возвращенно не представление
* а боди!! те json, так как по умолчанию в зависимости web spring используется jackson, но он в принципе и хорош
* для данной задачи!!! , также мы можемвозвращать что угодно, любыве объекты ,даже Pesrson все оно смапится в
*Jacksonom */
    //@ResponseBody потому что пометили  @RestController наш класс!!!

    @GetMapping("/sayHello")
    public String sayHello()
    {
            return "Hello world";
    }


}
