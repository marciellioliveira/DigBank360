package br.com.marcielli.DigBank360;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.marcielli.DigBank360.clientes", "br.com.marcielli.DigBank360.contas","br.com.marcielli.DigBank360.contas.corrente", "br.com.marcielli.DigBank360.contas.poupanca"})
public class DigBank360Application {

	public static void main(String[] args) {
		SpringApplication.run(DigBank360Application.class, args);
	}

}
