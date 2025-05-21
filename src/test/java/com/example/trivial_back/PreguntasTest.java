package com.example.trivial_back;


import com.example.trivial_back.Servicios.TrivialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PreguntasTest {

    // Inyección de dependencias, en este caso de la clase TrivialService
    @Autowired
    private TrivialService trivialService;



}
