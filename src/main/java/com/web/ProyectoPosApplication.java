package com.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.web.dao.BoletaDao;


@SpringBootApplication
public class ProyectoPosApplication {

	public static void main(String[] args) {
//		SpringApplication.run(ProyectoPosApplication.class, args);
		
		
		AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(AppConfig.class);
		BoletaDao boletadao = acac.getBean(BoletaDao.class);
		System.out.println(boletadao.findAll());
	}

}
