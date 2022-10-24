package com.catalisa4.zupperMarket.controller;

import com.catalisa4.zupperMarket.model.AnuncioModel;
import com.catalisa4.zupperMarket.model.UsuarioModel;
import com.catalisa4.zupperMarket.service.AnuncioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.catalisa4.zupperMarket.enums.Categorias.TECNOLOGIA;
import static com.catalisa4.zupperMarket.enums.FormasDeEntrega.TRANSPORTADORA;
import static org.junit.jupiter.api.Assertions.*;
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



    @Test
    public void salvarNovoAnuncio_emCasoDeSucessoAoSalvar_deveRetornar201() throws Exception {
        AnuncioRequest anuncioRequest = new AnuncioRequest("Titulo", "esse é um bla", "http://celular.com", "tem um celular",
                1000, true, TECNOLOGIA,
                1, "PE", "pETROLINA", TRANSPORTADORA, new UsuarioModel("Vitoria", "vi",
                "vitoria@zup.com", "8788586589", "12345678"));

        Mockito.when(anuncioService.cadastrarNovoAnuncio(anuncioRequest.toAnuncioModel())).thenReturn(anuncioRequest.toAnuncioModel());
        this.mockMvc.perform(post("/anuncios")
                .content(asJsonString(anuncioRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}