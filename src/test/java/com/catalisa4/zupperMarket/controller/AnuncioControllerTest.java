package com.catalisa4.zupperMarket.controller;

import com.catalisa4.zupperMarket.controller.AnuncioController;
import com.catalisa4.zupperMarket.enums.Categoria;
import com.catalisa4.zupperMarket.enums.FormaDeEntrega;
import com.catalisa4.zupperMarket.model.AnuncioModel;
import com.catalisa4.zupperMarket.model.UsuarioModel;
import com.catalisa4.zupperMarket.service.AnuncioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
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
@SpringBootTest
class AnuncioControllerTest {

    public static final String I_PHONE_11 = "IPhone11";
    public static final String NOME_DO_TITULO = I_PHONE_11;
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

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AnuncioService anuncioService;

    private AnuncioModel anuncioModel;

    private AnuncioRequest anuncioRequest;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        startAnuncio();
    }




/*    @Test
    public void salvarNovoAnuncio_emCasoDeSucessoAoSalvar_deveRetornar201() throws Exception {
        AnuncioRequest anuncioRequest = new AnuncioRequest("Titulo", "esse é um bla", "http://celular.com", "tem um celular",
                1000, true, TECNOLOGIA, 1,
                "PE", "PETROLINA", TRANSPORTADORA, 1);

        Mockito.when(anuncioService.cadastrarNovoAnuncio(anuncioRequest.toAnuncioModel())).thenReturn(anuncioRequest.toAnuncioModel());
        this.mockMvc.perform(post("/anuncios")
                .content(asJsonString(anuncioRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());


    }*/

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void startAnuncio(){
        anuncioModel = new AnuncioModel(NOME_DO_TITULO, DESCRICAO, URL_FOTO, DESCRICAO_FOTO, VALOR, SE_NEGOCIAVEL, CATEGORIA, QUANTIDADE, ESTADO, CIDADE, FORMA_DE_ENTREGA);
        //anuncioRequest = new AnuncioRequest(URL_FOTO, DESCRICAO_FOTO);
    }

}

