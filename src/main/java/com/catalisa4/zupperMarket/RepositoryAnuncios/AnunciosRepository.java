package com.catalisa4.zupperMarket.RepositoryAnuncios;

import com.catalisa4.zupperMarket.ModelAnuncios.AnunciosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnunciosRepository extends JpaRepository<AnunciosModel, Long> {
}
