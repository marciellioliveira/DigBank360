package br.com.marcielli.DigBank360;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"br.com.marcielli.DigBank360.contas","br.com.marcielli.DigBank360.contas.corrente","br.com.marcielli.DigBank360.contas.poupanca","br.com.marcielli.DigBank360.helpers","br.com.marcielli.DigBank360.exception","br.com.marcielli.DigBank360.exception.clientes","br.com.marcielli.DigBank360.exception.contas"})
public class DigBank360Application {

	public static void main(String[] args) {
		SpringApplication.run(DigBank360Application.class, args);
	}

}
