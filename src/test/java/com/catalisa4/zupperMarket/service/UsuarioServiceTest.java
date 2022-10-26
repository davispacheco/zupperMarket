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
import static org.mockito.Mockito.*;

@SpringBootTest
public class UsuarioServiceTest {


    public static final Long ID = 1L;
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
        when(iUsuarioRepository.findById(anyLong())).thenThrow(new EntityNotFoundException(ERRO_ID_NAO_ENCONTRADO));

        try {
            usuarioService.exibirUsuarioPorId(1L);
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
        Optional<UsuarioModel> response = usuarioService.buscarUsuarioPorEmail(EMAIL);

        //teste para confirmar que o objeto criado não é nulo
        assertNotNull(response);

        //teste para confirmar o retorno do metodo
        assertEquals(EMAIL, response.get().getEmail());
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
        assertEquals(NOME_COMPLETO, response.getNomeCompleto());
        assertEquals(APELIDO, response.getApelido());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(CELULAR, response.getCelular());
        assertEquals(SENHA, response.getSenha());
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

    //teste do metodo de deletar usuario cadastrado
    @Test
    void deletarComSucesso(){
        when(iUsuarioRepository.findById(anyLong())).thenReturn(optionalUsuarioModel);
        doNothing().when(iUsuarioRepository).deleteById(anyLong());
   
        usuarioService.deletarUsuario(ID);
        verify(iUsuarioRepository, times(1)).deleteById(anyLong());
    }

   //Criando metodo para 'iniciar' os testes
    private void startUsuario(){
        usuarioModel = new UsuarioModel(NOME_COMPLETO, APELIDO, EMAIL, CELULAR, SENHA);
        optionalUsuarioModel = Optional.of(new UsuarioModel(NOME_COMPLETO, APELIDO, EMAIL, CELULAR, SENHA));
    }
}
