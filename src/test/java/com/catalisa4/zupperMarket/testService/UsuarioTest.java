package com.catalisa4.zupperMarket.testService;

import com.catalisa4.zupperMarket.exception.EntityNotFoundException;
import com.catalisa4.zupperMarket.model.UsuarioModel;
import com.catalisa4.zupperMarket.repository.IUsuarioRepository;
import com.catalisa4.zupperMarket.service.UsuarioService;
import org.hibernate.ObjectNotFoundException;
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

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UsuarioTest {

    public static final int id = 1;
    public static final String NOME_COMPLETO = "Katia Queiroz";
    public static final String APELIDO = "katinha";
    public static final String EMAIL = "katia@zup.com.br";
    public static final String CELULAR = "3499999999";
    public static final String SENHA = "1234";
    public static final String ERRO_ID_NAO_ENCONTRADO = "Erro: id não encontrado. ";


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

  /*  @Test
    @DisplayName("Testando o metodo de cadastrar")
    void testeSeCadastroDeUsuarioEstaFuncionando(){
        iUsuarioRepository.save(usuarioModel);
        when(iUsuarioRepository.existsBYEmail(Mockito.anyString())).thenReturn(true);
        Mockito.verify(iUsuarioRepository, Mockito.times(1)).save(usuarioModel);
    }*/

//    @Test
//    @DisplayName("O teste deve retornar true, se o metodo de buscar por id estiver funcionando corretamente")
//    void testeDeBuscarUsuarioViaId() {
//        usuarioService.exibirUsuarioPorId(1L);
//        Assertions.assertTrue(true);
//    }

    //Testando metodo para verificar email

    //Testando metodo de buscar usuario por ID
    @Test
    void quandoFizerABuscaPorIdRetornarAInstanciaDeUmUsuario(){
        when(iUsuarioRepository.findById(anyLong())).thenReturn(optionalUsuarioModel);

        //fazendo a chamada do metodo
        UsuarioModel response = usuarioService.exibirUsuarioPorId(Long.valueOf(id));

        //teste para confirmar que o objeto criado não é nulo
        assertNotNull(response);

        //teste para confirmar a classe chamada
        assertEquals(UsuarioModel.class, response.getClass());
    }

    //teste para verificar a execucaçao do metodo mesmo quando o id não é encontrado
    @Test
    void quandoNaoEncontrarOIdRetornarAExcecaoDoObjetoNaoEncontrado(){
        when(iUsuarioRepository.findById(anyLong())).thenThrow(new EntityNotFoundException(ERRO_ID_NAO_ENCONTRADO));

        try {
            usuarioService.exibirUsuarioPorId(Long.valueOf(id));
        }catch (Exception ex){
            assertEquals(EntityNotFoundException.class, ex.getClass());
            //teste para garantir que a msg de erro é igual a msg de erro esperada
            assertEquals(ERRO_ID_NAO_ENCONTRADO, ex.getMessage());
        }
    }


    //Criando metodo para 'iniciar' os testes
    private void startUsuario(){
        usuarioModel = new UsuarioModel(NOME_COMPLETO, APELIDO, EMAIL, CELULAR, SENHA);
        optionalUsuarioModel = Optional.of(new UsuarioModel(NOME_COMPLETO, APELIDO, EMAIL, CELULAR, SENHA));
    }



}
