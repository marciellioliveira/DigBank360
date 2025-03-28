package br.com.marcielli.DigBank360.contas.poupanca;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoupancaRepository extends JpaRepository<Poupanca, Long> {

}
