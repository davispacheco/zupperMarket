package com.catalisa4.zupperMarket.controller;

import com.catalisa4.zupperMarket.model.UsuarioModel;
import com.catalisa4.zupperMarket.service.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UsuarioControllerTest {

    private static final Long ID = 1L;
    private static final String NOME_COMPLETO = "Usuario Zupper";
    private static final String APELIDO = "zuppinhx";
    private static final String EMAIL = "usuario@zup.com.br";
    private static final String CELULAR = "3499999999";
    private static final String SENHA = "1234";

    private UsuarioModel usuarioModel;
    private UsuarioRequest usuarioRequest;

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        startUsuario();
    }

    //teste para verificar o metodo de cadastro de usuario
    @Test
    void quandoCadastrarUsuario_RetornarCreated(){
        when(usuarioService.cadastrar(any())).thenReturn(usuarioModel);

        ResponseEntity<UsuarioResponse> response = usuarioController.cadastrarUsuario(usuarioRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
    }

    //teste para deletar usuario
    @Test
    void quandoDeletar_RetornarSucesso(){
        doNothing().when(usuarioService).deletarUsuario(anyLong());

        ResponseEntity<?> response = usuarioController.deletarUsuario(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        verify(usuarioService, times(1)).deletarUsuario(anyLong());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }





    //Criando metodo para 'iniciar' os testes
    private void startUsuario(){
        usuarioModel = new UsuarioModel(NOME_COMPLETO, APELIDO, EMAIL, CELULAR, SENHA);
        usuarioRequest = new UsuarioRequest();
    }

}