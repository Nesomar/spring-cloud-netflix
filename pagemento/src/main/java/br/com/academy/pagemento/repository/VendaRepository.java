package br.com.academy.pagemento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.academy.pagemento.entity.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

}