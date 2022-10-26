package com.catalisa4.zupperMarket.controller;

import com.catalisa4.zupperMarket.controller.AnuncioController;
import com.catalisa4.zupperMarket.enums.Categoria;
import com.catalisa4.zupperMarket.enums.FormaDeEntrega;
import com.catalisa4.zupperMarket.model.AnuncioModel;
import com.catalisa4.zupperMarket.model.UsuarioModel;
import com.catalisa4.zupperMarket.service.AnuncioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.catalisa4.zupperMarket.enums.Categoria.TECNOLOGIA;
import static com.catalisa4.zupperMarket.enums.FormaDeEntrega.TRANSPORTADORA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnuncioController.class)
@AutoConfigureMockMvc
class AnuncioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AnuncioService anuncioService;
    private AnuncioModel anuncioModel;

    private AnuncioRequest anuncioRequest;



/*
    @Test
    public void salvarNovoAnuncio_emCasoDeSucessoAoSalvar_deveRetornar201() throws Exception {
        AnuncioRequest anuncioRequest = new AnuncioRequest("Titulo", "esse é um bla", "http://celular.com", "tem um celular",
                1000, true, Categoria.TECNOLOGIA,
                "PE", "PETROLINA", FormaDeEntrega.TRANSPORTADORA, 1);

        Mockito.when(anuncioService.cadastrarNovoAnuncio(anuncioRequest.toAnuncioModel())).thenReturn(anuncioRequest.toAnuncioModel());
        this.mockMvc.perform(post("/anuncios")
                .content(asJsonString(anuncioRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
*/

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}

