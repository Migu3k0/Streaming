package com.example.ms_categorias;

import com.example.ms_categorias.model.*;
import com.example.ms_categorias.repository.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            Categoria categoria = new Categoria();
            categoria.setNombre(faker.book().genre());
            categoriaRepository.save(categoria);
        }
    }
}