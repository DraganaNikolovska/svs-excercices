package com.services.config;

import com.data_access.*;
import com.services.LibraryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dragana.nikolovska on 9/27/2016.
 */
@Configuration
public class ServiceConfig {
    @Bean
    public LibraryService libraryService(BookDao bookDao, MemberDao memberDao, MagazineDao magazineDao, LoanDao loanDao, PublicationDao publicationDao){
        return new LibraryService(bookDao, memberDao, magazineDao, loanDao, publicationDao);
    }
}
