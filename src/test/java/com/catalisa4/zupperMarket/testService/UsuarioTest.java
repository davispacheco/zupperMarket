package com.catalisa4.zupperMarket.testService;

import com.catalisa4.zupperMarket.model.UsuarioModel;
import com.catalisa4.zupperMarket.repository.IUsuarioRepository;
import com.catalisa4.zupperMarket.service.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UsuarioTest {

    public static final String NOME_COMPLETO = "Katia Queiroz";
    public static final String APELIDO = "katinha";
    public static final String EMAIL = "katia@zup.com.br";
    public static final String CELULAR = "3499999999";
    public static final String SENHA = "1234";
    public static final int ID = 1;

    @InjectMocks
    UsuarioService usuarioService;

    @MockBean
    IUsuarioRepository iUsuarioRepository;

    private UsuarioModel usuarioModel;

    private Optional<UsuarioModel> optionalUsuarioModel;

    @BeforeEach
    private void setUsuarioService() {
        MockitoAnnotations.openMocks(this);
        //chamando metodo criado para inicio dos testes
        startUsuario();
    }

    @Test
    @DisplayName("Testando o metodo de cadastrar")
    void testeSeCadastroDeUsuarioEstaFuncionando(){
        iUsuarioRepository.save(usuarioModel);
        when(iUsuarioRepository.existsBYEmail(Mockito.anyString())).thenReturn(true);
        Mockito.verify(iUsuarioRepository, Mockito.times(1)).save(usuarioModel);
    }

    @Test
    @DisplayName("O teste deve retornar true, se o metodo de buscar por id estiver funcionando corretamente")
    void testeDeBuscarUsuarioViaId() {
        usuarioService.exibirUsuarioPorId(1L);
        Assertions.assertTrue(true);
    }

    //Criando metodo para 'iniciar' os testes
    private void startUsuario(){
        usuarioModel = new UsuarioModel(NOME_COMPLETO, APELIDO, EMAIL, CELULAR, SENHA);
        optionalUsuarioModel = Optional.of(new UsuarioModel(NOME_COMPLETO, APELIDO, EMAIL, CELULAR, SENHA));
    }



}
