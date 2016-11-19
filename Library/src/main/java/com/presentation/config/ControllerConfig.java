package com.presentation.config;

import com.presentation.*;
import com.services.LibraryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration;

/**
 * Created by dragana.nikolovska on 9/27/2016.
 */
@Configuration
@ComponentScan
public class ControllerConfig {

    @Bean
    public LibraryController libraryController(LibraryService libraryService){

        return new LibraryController(libraryService);
    }

    @Bean
    public BookController bookController(LibraryService libraryService){
        return new BookController(libraryService);
    }

    @Bean
    public BookRestController bookRestController(LibraryService libraryService){

        return new BookRestController(libraryService);
    }
    @Bean
    public IndexController indexController(){
        return new IndexController();
    }
    @Bean
    public LoanController loanController(LibraryService libraryService){

        return new LoanController(libraryService);
    }
    @Bean
    public MagazineController magazineController(LibraryService libraryService){

        return new MagazineController(libraryService);
    }
    @Bean
    public MemberController memberController(LibraryService libraryService){

        return new MemberController(libraryService);
    }
    @Bean
    public PublicationController publicationController(LibraryService libraryService){
        return new PublicationController(libraryService);
    }
    @Bean
    public Swagger2DocumentationConfiguration swagger2DocumentationConfiguration(){
        return new Swagger2DocumentationConfiguration();
    }
}
