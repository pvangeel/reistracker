package org.goldflower.config;

import org.goldflower.account.Account;
import org.goldflower.account.AccountRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DataSetLoader {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ApplicationConfig.class);
        context.register(SecurityConfig.class);
        context.register(JpaConfig.class);
        context.refresh();
        AccountRepository accountRepository = (AccountRepository) context.getBean("accountRepository");
        accountRepository.save(new Account(args[0], args[1], "ROLE_ADMIN"));
    }
}
