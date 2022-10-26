package com.catalisa4.zupperMarket.repository;

import com.catalisa4.zupperMarket.enums.Categoria;
import com.catalisa4.zupperMarket.enums.Status;
import com.catalisa4.zupperMarket.model.AnuncioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAnuncioRepository extends JpaRepository<AnuncioModel, Long> {
    @Query("from AnuncioModel where status = :status")
    List<AnuncioModel> findByStatus(@Param("status") Status status);

@Query("from AnuncioModel where status = :status and categoria = :categoria")
List<AnuncioModel> findByStatusAndCategoria(@Param("status") Status status, @Param("categoria") Categoria categoria);
}
