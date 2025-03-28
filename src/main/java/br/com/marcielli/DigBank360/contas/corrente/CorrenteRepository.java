package br.com.marcielli.DigBank360.contas.corrente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorrenteRepository extends JpaRepository<Corrente, Long> {

}
