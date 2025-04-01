package br.com.marcielli.DigBank360.contas.corrente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.marcielli.DigBank360.contas.ContaRepository;

@Repository
public interface CorrenteRepository extends ContaRepository {

	

}
