package com.catalisa4.zupperMarket.service;

import com.catalisa4.zupperMarket.exception.DataIntegratyViolationException;
import com.catalisa4.zupperMarket.exception.EntityNotFoundException;
import com.catalisa4.zupperMarket.model.UsuarioModel;
import com.catalisa4.zupperMarket.repository.IUsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UsuarioServiceTest {

    public static final int id = 1;
    public static final String NOME_COMPLETO = "Usuario Zupper";
    public static final String APELIDO = "zuppinhx";
    public static final String EMAIL = "usuario@zup.com.br";
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
    void quandoFizerABuscaPorId(){
        when(iUsuarioRepository.findById(anyLong())).thenReturn(optionalUsuarioModel);

        //criando o objeto para chamar o metodo
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

    //Teste para verificar busca por email
    @Test
    void quandoFizerABuscaPorEmail(){
        when(iUsuarioRepository.findByEmail(anyString())).thenReturn(optionalUsuarioModel);

        //criando o objeto para chamar o metodo
        Optional<UsuarioModel> responseEmail = usuarioService.buscarUsuarioPorEmail(EMAIL);

        //teste para confirmar que o objeto criado não é nulo
        assertNotNull(responseEmail);

        //teste para confirmar a classe chamada
        assertEquals(UsuarioModel.class, responseEmail.getClass());
        //teste para confirmar o retorno do metodo
        assertEquals(EMAIL, responseEmail.get());
    }

    //teste do metodo de cadastro do usuario
    @Test
    void quandoRealizarCadastro_RetornarSucesso(){
        when(iUsuarioRepository.save(any())).thenReturn(usuarioModel);

        UsuarioModel responseCadastro = usuarioService.cadastrar(usuarioModel);

        //teste para confirmar que o objeto criado não é nulo
        assertNotNull(responseCadastro);

        //teste para confirmar a classe chamada
        assertEquals(UsuarioModel.class, responseCadastro.getClass());

        //teste para confirmar o retorno do metodo
        assertEquals(NOME_COMPLETO, responseCadastro.getNomeCompleto());
        assertEquals(APELIDO, responseCadastro.getApelido());
        assertEquals(EMAIL, responseCadastro.getEmail());
        assertEquals(CELULAR, responseCadastro.getCelular());
        assertEquals(SENHA, responseCadastro.getSenha());
    }

    @Test
    void quandoRealizarCadastro_RetornarExcecaoDeViolacaoDeIntegridade(){
        when(iUsuarioRepository.findByEmail(anyString())).thenReturn(optionalUsuarioModel);

        try{
            optionalUsuarioModel.get().setId(Long.valueOf(2));
            usuarioService.cadastrar(usuarioModel);
        }catch (Exception ex){
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals("E-mail já existe!", ex.getMessage());
        }
    }








    //Criando metodo para 'iniciar' os testes
    private void startUsuario(){
        usuarioModel = new UsuarioModel(NOME_COMPLETO, APELIDO, EMAIL, CELULAR, SENHA);
        optionalUsuarioModel = Optional.of(new UsuarioModel(NOME_COMPLETO, APELIDO, EMAIL, CELULAR, SENHA));
    }



}
