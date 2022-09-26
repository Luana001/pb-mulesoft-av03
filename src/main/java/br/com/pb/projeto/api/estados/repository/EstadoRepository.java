package br.com.pb.projeto.api.estados.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pb.projeto.api.estados.model.Estado;
import br.com.pb.projeto.api.estados.model.RegiaoEstado;

public interface EstadoRepository extends JpaRepository<Estado, Integer>{

	Page<Estado> findByRegiao(RegiaoEstado regiao, Pageable paginacao);

}
