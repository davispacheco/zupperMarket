package com.catalisa4.zupperMarket.service;

import com.catalisa4.zupperMarket.exception.DataIntegratyViolationException;
import com.catalisa4.zupperMarket.exception.EntityNotFoundException;
import com.catalisa4.zupperMarket.model.UsuarioModel;
import com.catalisa4.zupperMarket.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    public UsuarioModel cadastrar(UsuarioModel usuarioModel) {
        validarEmailExistente(usuarioModel.getEmail());
        return iUsuarioRepository.save(usuarioModel);
    }

    public UsuarioModel exibirUsuarioPorId(Long id) {
        Optional<UsuarioModel> obj = iUsuarioRepository.findById(id);
        obj.orElseThrow((() -> new EntityNotFoundException("Erro: id não encontrado. " + id)));
        return obj.get();
    }


    public void deletarUsuario(Long id) {
        iUsuarioRepository.deleteById(id);
    }


    public void validarEmailExistente(String email) {
        Optional<UsuarioModel> obj = buscarUsuarioPorEmail(email);
        if (obj.isPresent()) {
            throw new DataIntegratyViolationException("E-mail já existe!");
        }
    }

    //Validação(Query method)
    public Optional<UsuarioModel> buscarUsuarioPorEmail(String email) {
        return iUsuarioRepository.findByEmail(email);
    }
}
