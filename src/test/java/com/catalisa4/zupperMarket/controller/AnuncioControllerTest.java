package com.catalisa4.zupperMarket.controller;

import com.catalisa4.zupperMarket.controller.AnuncioController;
import com.catalisa4.zupperMarket.enums.Categoria;
import com.catalisa4.zupperMarket.enums.FormaDeEntrega;
import com.catalisa4.zupperMarket.model.AnuncioModel;
import com.catalisa4.zupperMarket.model.UsuarioModel;
import com.catalisa4.zupperMarket.service.AnuncioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static com.catalisa4.zupperMarket.enums.Categoria.TECNOLOGIA;
import static com.catalisa4.zupperMarket.enums.FormaDeEntrega.TRANSPORTADORA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(AnuncioController.class)
//@AutoConfigureMockMvc
@SpringBootTest
class AnuncioControllerTest {

    public static final int INDEX = 0;
    public static final Long ID = 1L;
    public static final String I_PHONE_11 = "IPhone11";
    //public static final String NOME_DO_TITULO = I_PHONE_11;
    public static final String DESCRICAO = "Em otimo estado";
    public static final String URL_FOTO = "url:qualquer";
    public static final String DESCRICAO_FOTO = "aparelho preto";
    public static final int VALOR = 1500;
    public static final boolean SE_NEGOCIAVEL = true;
    public static final Categoria CATEGORIA = TECNOLOGIA;
    public static final int QUANTIDADE = 1;
    public static final String ESTADO = "SP";
    public static final String CIDADE = "São Paulo";
    public static final FormaDeEntrega FORMA_DE_ENTREGA = TRANSPORTADORA;


    @InjectMocks
    private AnuncioController anuncioController;

    private AnuncioModel anuncioModel;
    private AnuncioRequest anuncioRequest;


//    @Autowired
//    private MockMvc mockMvc;

    @Mock
    AnuncioService anuncioService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        startAnuncio();
    }

    //teste de buscar anuncio por Id
    @Test
    void quandoBuscarPorId_RetornarSucesso(){
        when(anuncioService.buscarPorId(anyLong())).thenReturn(anuncioModel);

        ResponseEntity<AnuncioModel> response = anuncioController.buscarAnuncioPorId(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(AnuncioModel.class, response.getBody().getClass());
    }

    //teste para buscar lista de anuncios
    @Test
    void quandoBuscarPorAnuncios_RetornarListaDeAnuncios(){
        when(anuncioService.buscarTodosAnuncios()).thenReturn(List.of(anuncioModel));

        ResponseEntity<List<AnuncioResponse>> response = anuncioController.buscarAnuncios();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(AnuncioResponse.class, response.getBody().get(INDEX).getClass());
    }

    //teste para buscar lista de anuncios por status
    @Test
    void quandoBuscarPorAnunciosPorStatus_RetornarStatus(){
        when(anuncioService.buscarPorStatus(anuncioModel.getStatus())).thenReturn(List.of(anuncioModel));

        ResponseEntity<List<AnuncioResponse>> response = anuncioController.buscarAnunciosPorStatus(anuncioModel.getStatus());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(AnuncioResponse.class, response.getBody().get(INDEX).getClass());
    }

    //teste para criar anuncio
    @Test
    void quandoCriarAnuncio_RetornarCreated(){
        when(anuncioService.cadastrarNovoAnuncio(any(), anyLong())).thenReturn(anuncioModel);
        when(anuncioRequest.toAnuncioModel()).thenReturn(anuncioModel);

        ResponseEntity<AnuncioResponse> response = anuncioController.cadastrarAnuncio(anuncioRequest);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    //teste para buscar lista de anuncios por status epor categoria
    @Test
    void quandoBuscarPorAnunciosPorStatusECategoria_RetornarStatusECategoria(){
        when(anuncioService.buscarPorStatusECategoria(anuncioModel.getStatus(), anuncioRequest.getCategoria())).thenReturn(List.of(anuncioModel));

        ResponseEntity<List<AnuncioResponse>> response = anuncioController.buscarAnunciosPorStatusECategorias(anuncioModel.getStatus(), anuncioRequest.getCategoria());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(AnuncioResponse.class, response.getBody().get(INDEX).getClass());
    }

    //teste para alterar cadastro de anuncio
    @Test
    void quandoAlterarAnuncio_RetornarSucesso(){
        when(anuncioService.alterarAnuncio(anuncioModel)).thenReturn(anuncioModel);
        when(anuncioRequest.toAnuncioModel()).thenReturn(anuncioModel);

        ResponseEntity<AnuncioResponse> response = anuncioController.alterarAnuncio(anuncioRequest, ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(AnuncioResponse.class, response.getBody().getClass());
    }






//    public static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    private void startAnuncio(){
        anuncioModel = new AnuncioModel(I_PHONE_11, DESCRICAO, URL_FOTO, DESCRICAO_FOTO, VALOR, SE_NEGOCIAVEL, CATEGORIA, QUANTIDADE, ESTADO, CIDADE, FORMA_DE_ENTREGA);
        //anuncioRequest = new AnuncioRequest();
    }

}

