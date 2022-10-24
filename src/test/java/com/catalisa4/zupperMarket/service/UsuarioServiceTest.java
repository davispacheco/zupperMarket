package com.catalisa4.zupperMarket.service;

import com.catalisa4.zupperMarket.exception.DataIntegratyViolationException;
import com.catalisa4.zupperMarket.exception.EntityNotFoundException;
import com.catalisa4.zupperMarket.model.AnuncioModel;
import com.catalisa4.zupperMarket.model.UsuarioModel;
import com.catalisa4.zupperMarket.repository.IUsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.yaml.snakeyaml.events.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.catalisa4.zupperMarket.service.AnuncioServiceTest.ID;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UsuarioServiceTest {


    public static final Long id = 1L;
    public static final String nomeCompleto = "Usuario Zupper";
    public static final String apelido = "zuppinhx";
    public static final String email = "usuario@zup.com.br";
    public static final String celular = "3499999999";
    public static final String senha = "1234";
    public static final List<AnuncioModel> anuncios = new ArrayList<>();
    public static final String erroIdNaoEncontrado = "Erro: id não encontrado. ";


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
    void quandoFizerABuscaPorId(){
        when(iUsuarioRepository.findById(anyLong())).thenReturn(optionalUsuarioModel);

        //criando o objeto para chamar o metodo
        UsuarioModel response = usuarioService.exibirUsuarioPorId(1L);

        //teste para confirmar que o objeto criado não é nulo
        assertNotNull(response);

        //teste para confirmar a classe chamada
        assertEquals(UsuarioModel.class, response.getClass());
    }

    //teste para verificar a execucaçao do metodo mesmo quando o id não é encontrado
    @Test
    void quandoNaoEncontrarOIdRetornarAExcecaoDoObjetoNaoEncontrado(){
        when(iUsuarioRepository.findById(anyLong())).thenThrow(new EntityNotFoundException(erroIdNaoEncontrado));

        try {
            usuarioService.exibirUsuarioPorId(1L);
        }catch (Exception ex){
            assertEquals(EntityNotFoundException.class, ex.getClass());
            //teste para garantir que a msg de erro é igual a msg de erro esperada
            assertEquals(erroIdNaoEncontrado, ex.getMessage());
        }
    }

    //Teste para verificar busca por email
    @Test
    void quandoFizerABuscaPorEmail(){
        when(iUsuarioRepository.findByEmail(anyString())).thenReturn(optionalUsuarioModel);

        //criando o objeto para chamar o metodo
        Optional<UsuarioModel> response = usuarioService.buscarUsuarioPorEmail(email);

        //teste para confirmar que o objeto criado não é nulo
        assertNotNull(response);

        //teste para confirmar a classe chamada
        assertEquals(UsuarioModel.class, response.getClass());
        //teste para confirmar o retorno do metodo
        assertEquals(email, response.get());
    }

    //teste do metodo de cadastro do usuario
    @Test
    void quandoRealizarCadastro_RetornarSucesso(){
        when(iUsuarioRepository.save(any())).thenReturn(usuarioModel);

        UsuarioModel response = usuarioService.cadastrar(usuarioModel);

        //teste para confirmar que o objeto criado não é nulo
        assertNotNull(response);

        //teste para confirmar a classe chamada
        assertEquals(UsuarioModel.class, response.getClass());

        //teste para confirmar o retorno do metodo
        assertEquals(id, response.getId());
        assertEquals(nomeCompleto, response.getNomeCompleto());
        assertEquals(apelido, response.getApelido());
        assertEquals(email, response.getEmail());
        assertEquals(celular, response.getCelular());
        assertEquals(senha, response.getSenha());
    }

    //teste do metodo de cadastro do usuario com excessao
    @Test
    void quandoRealizarCadastro_RetornarExcecaoDeViolacaoDeIntegridade(){
        when(iUsuarioRepository.findByEmail(anyString())).thenReturn(optionalUsuarioModel);

        try{
            optionalUsuarioModel.get().setId(1L);
            usuarioService.cadastrar(usuarioModel);
        }catch (Exception ex){
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals("E-mail já existe!", ex.getMessage());
        }
    }

    //teste do metodo para atualizar cadastro
    @Test
    void quandoAtualizarCadastro_RetornarSucesso(){
        when(iUsuarioRepository.save(any())).thenReturn(usuarioModel);

        UsuarioModel response = usuarioService.atualizarUsuarioCadastrado(usuarioModel, id);

        //teste para confirmar que o objeto criado não é nulo
        assertNotNull(response);

        //teste para confirmar a classe chamada
        assertEquals(UsuarioModel.class, response.getClass());

        //teste para confirmar o retorno do metodo
        assertEquals(id, response.getId());
        assertEquals(nomeCompleto, response.getNomeCompleto());
        assertEquals(apelido, response.getApelido());
        assertEquals(email, response.getEmail());
        assertEquals(celular, response.getCelular());
        assertEquals(senha, response.getSenha());
    }









    //Criando metodo para 'iniciar' os testes
    private void startUsuario(){
        usuarioModel = new UsuarioModel(id, nomeCompleto, email, celular, senha, anuncios);
        optionalUsuarioModel = Optional.of(new UsuarioModel(id, nomeCompleto, apelido, email, celular, senha, anuncios));
    }



}