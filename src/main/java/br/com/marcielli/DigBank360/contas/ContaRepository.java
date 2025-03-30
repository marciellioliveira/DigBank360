package br.com.marcielli.DigBank360.contas;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{

	List<Conta> findAllById(Long id);
	

}
