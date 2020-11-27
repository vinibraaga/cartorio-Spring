package com.docket.cartorio.repositories;

import com.docket.cartorio.entities.Certidao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CertidaoRepository extends JpaRepository<Certidao, Integer> {

    List<Certidao> findByCartorioId(Integer id);

    boolean existsByIdCertidaoAndCartorio(Integer idCertidao, Integer id);

    @Transactional
    @Modifying
    @Query("delete from Certidao where idCertidao is ?1 and cartorio.id is ?2")
    int deletarCertidao(Integer idCertidao, Integer id);
}
