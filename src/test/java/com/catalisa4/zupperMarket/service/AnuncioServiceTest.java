package com.catalisa4.zupperMarket.service;

import com.catalisa4.zupperMarket.enums.Categoria;
import com.catalisa4.zupperMarket.enums.FormaDeEntrega;
import com.catalisa4.zupperMarket.enums.Status;
import com.catalisa4.zupperMarket.model.AnuncioModel;

import com.catalisa4.zupperMarket.model.UsuarioModel;
import com.catalisa4.zupperMarket.repository.IAnuncioRepository;
import com.catalisa4.zupperMarket.repository.IUsuarioRepository;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.catalisa4.zupperMarket.enums.Categoria.TECNOLOGIA;
import static com.catalisa4.zupperMarket.enums.FormaDeEntrega.TRANSPORTADORA;
import static com.catalisa4.zupperMarket.enums.Status.DISPONIVEL;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AnuncioServiceTest {


    public static final String nomeDoTitulo = "Katia Queiroz";
    public static final String descricao = "Esse celular é muito bom ";
    public static final String urlFoto = "https://www.apple.com/br/shop/buy-iphone/iphone-14-pro";
    public static final String descricaoFoto = "è uma aparalho celular";
    public static final Double valor = 1000.00;
    public static final Long ID = 1L;
    public static final Categoria categoria = TECNOLOGIA;
    public static final int quantidade = 1;
    public static final String estado = "São Paulo";
    public static final String cidade = "São Paulo";
    public static final FormaDeEntrega entrega = TRANSPORTADORA;
    public static final Status status = DISPONIVEL;



    @MockBean
    IAnuncioRepository iAnuncioRepository;
    @MockBean
    IUsuarioRepository iUsuarioRepository;

    @InjectMocks
    AnuncioService anuncioService;

    private AnuncioModel anuncioModel;

    private Optional<UsuarioModel> optionalUsuarioModel;
    private Optional<AnuncioModel> optionalAnuncioModel;


    @BeforeEach
    private void setAnuncioService() {
        anuncioModel = new AnuncioModel(1L, "Titulo", "esse é um bla", "http://celular.com", "tem um celular",
                1000, true, TECNOLOGIA,
                1, "PE", "PETROLINA", TRANSPORTADORA);
        UsuarioModel usuarioModel = new UsuarioModel(1L, "Vitoria", "vi",
                "vitoria@zup.com", "8788586589", "12345678");
        optionalAnuncioModel = Optional.of(anuncioModel);
        optionalUsuarioModel = Optional.of(usuarioModel);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void cadastrarAnuncio() {
        when(iAnuncioRepository.save(anuncioModel)).thenReturn(anuncioModel);
        when(iUsuarioRepository.findById(ID)).thenReturn(optionalUsuarioModel);
        AnuncioModel response = anuncioService.cadastrarNovoAnuncio(anuncioModel, optionalUsuarioModel.get().getId());
        //anuncioService.cadastrarNovoAnuncio(anuncioModel, anuncioModel.getId());
        verify(iAnuncioRepository, Mockito.times(1)).save(response);
    }


    @Test
    void BuscarTodosAnuncios() {
        List<AnuncioModel> listTroca = new ArrayList<>();
        when(iAnuncioRepository.findAll()).thenReturn(listTroca);
        List<AnuncioModel> listTest = new ArrayList<>();
        listTest = anuncioService.buscarTodos();
        Assertions.assertEquals(listTest, listTroca);

    }

    @Test
    void buscaIdAnuncio() {
        AnuncioModel anuncioModel = new AnuncioModel();
        anuncioModel.setId(1L);
        when(iAnuncioRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(anuncioModel));
        var status = anuncioService.buscarPorId(ID);
        Assertions.assertEquals(status.getId(), anuncioModel.getId());
    }

    @Test
    void alterarAnuncio() {
        when(iAnuncioRepository.save(anuncioModel)).thenReturn(anuncioModel);
        when(iAnuncioRepository.findById(ID)).thenReturn(optionalAnuncioModel);
        anuncioService.alterarAnuncio(anuncioModel, ID);
        verify(iAnuncioRepository, Mockito.times(1)).save(anuncioModel);
    }

    @Test
    void deletarAnuncio() {
        anuncioService.deletarAnuncio(anuncioModel.getId());
        verify(iAnuncioRepository, Mockito.times(1)).deleteById(anuncioModel.getId());
    }
}

