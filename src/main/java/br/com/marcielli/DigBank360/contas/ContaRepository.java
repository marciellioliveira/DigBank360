package br.com.marcielli.DigBank360.contas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ContaRepository extends JpaRepository<Conta, Long> {

	List<Conta> findAllById(Long id);
	
	List<Conta> findAll();


	

}
