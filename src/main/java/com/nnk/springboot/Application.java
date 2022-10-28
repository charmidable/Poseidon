package com.nnk.springboot;


import com.nnk.springboot.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

//@EnableAutoConfiguration
@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
public class Application implements CommandLineRunner
{
    @Autowired
    TradeService tradeService;

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }


    @Override
    public void run(String... args) throws Exception
    {
        System.out.println(tradeService.getAll());
    }
}