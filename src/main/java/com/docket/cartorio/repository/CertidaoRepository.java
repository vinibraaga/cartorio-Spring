package com.docket.cartorio.repository;

import com.docket.cartorio.model.Certidao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CertidaoRepository extends JpaRepository<Certidao, Long> {

    List<Certidao> findByCartorioId(Long id);

    boolean existsByIdCertidaoAndCartorio(Long idCertidao, Long id);

    @Transactional
    @Modifying
    @Query("delete from Certidao where idCertidao is ?1 and cartorio.id is ?2")
    int deletarCertidao(Long idCertidao, Long id);
}
