package com.catalisa4.zupperMarket.repository;

import com.catalisa4.zupperMarket.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioModel, Long> {
Optional<UsuarioModel> findByEmail(String email);
}
