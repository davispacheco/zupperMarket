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
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static com.catalisa4.zupperMarket.enums.Categoria.GAMES;
import static com.catalisa4.zupperMarket.enums.Categoria.TECNOLOGIA;
import static com.catalisa4.zupperMarket.enums.FormaDeEntrega.TRANSPORTADORA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(AnuncioController.class)
@AutoConfigureMockMvc
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
    @Mock
    private AnuncioModel anuncioModel;
    @Mock
    private AnuncioRequest anuncioRequest;
    @InjectMocks
    private AnuncioController anuncioController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnuncioService anuncioService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        startAnuncio();
    }
    @Test
    public void salvarNovoAnuncio_emCasoDeSucessoAoSalvar_deveRetornar201() throws Exception {
        AnuncioRequest anuncioRequest = new AnuncioRequest("Titulo", "esse é um bla", "http://celular.com", "tem um celular",
                1000, true, TECNOLOGIA, 1,
                "PE", "PETROLINA", TRANSPORTADORA, 1L);
        when(anuncioService.cadastrarNovoAnuncio(any(), any())).thenReturn(anuncioRequest.toAnuncioModel());
        this.mockMvc.perform(post("/anuncios")
                        .content(asJsonString(anuncioRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void quandoBuscarAnuncioPorId_EmCasoDeSucesso_DeveRetornar201() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/anuncios/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void quandoBuscarTodosAnuncios_EmCasoDeSucesso_DeveRetornar201() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/anuncios")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void quandoBuscarAnuncioPorStatusECategoria_EmCasoDeSucesso_DeveRetornar201() throws Exception {

        mockMvc.perform( MockMvcRequestBuilders
                        .get("/anuncios?status=DISPONIVEL&categoria=TECNOLOGIA")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void quandoAtualizarAnuncio_EmCasoDeSucesso_DeveRetornar200() throws Exception {
        AnuncioRequest anuncioRequest = new AnuncioRequest("Titulo", "esse é um bla", "http://celular.com", "tem um celular",
                1000, true, TECNOLOGIA, 1,
                "PE", "PETROLINA", TRANSPORTADORA, 1L);
        when(anuncioService.alterarAnuncio(anuncioModel, ID)).thenReturn(anuncioRequest.toAnuncioModel());
        mockMvc.perform( MockMvcRequestBuilders
                        .put("/anuncios/{id}", 1L)
                        .content(asJsonString(anuncioModel))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();
//                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("firstName2"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("lastName2"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("email2@mail.com"));
    }
@Test
public void quandoDeletarAnuncio_EmCasoDeSucesso_DeveRetornar204() throws Exception{
    doNothing().when(anuncioService).deletarAnuncio(1L);
    mockMvc.perform( MockMvcRequestBuilders.delete("/anuncios/{id}", 1L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

}

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void startAnuncio(){
        anuncioModel = new AnuncioModel(ID, I_PHONE_11, DESCRICAO, URL_FOTO, DESCRICAO_FOTO, VALOR, SE_NEGOCIAVEL, CATEGORIA, QUANTIDADE, ESTADO, CIDADE, FORMA_DE_ENTREGA);
        //anuncioRequest = new AnuncioRequest();
    }

}

