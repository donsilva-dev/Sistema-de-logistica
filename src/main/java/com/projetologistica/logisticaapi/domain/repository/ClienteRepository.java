package com.projetologistica.logisticaapi.domain.repository;

import com.projetologistica.logisticaapi.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNome(String nome);
    Optional<Cliente> findByEmail(String email);
}
