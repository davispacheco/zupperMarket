package com.catalisa4.zupperMarket.testService;

import com.catalisa4.zupperMarket.model.AnuncioModel;
import com.catalisa4.zupperMarket.repository.IAnuncioRepository;
import com.catalisa4.zupperMarket.service.AnuncioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.catalisa4.zupperMarket.enums.Categorias.TECNOLOGIA;
import static com.catalisa4.zupperMarket.enums.FormasDeEntrega.TRANSPORTADORA;
import static com.catalisa4.zupperMarket.enums.Status.DISPONIVEL;

public class AnuncioTest {

    public static final String nomeDoTitulo = "Katia Queiroz";
    public static final String descricao = "Esse celular é muito bom ";
    public static final String urlFoto = "https://www.apple.com/br/shop/buy-iphone/iphone-14-pro";
    public static final String descricaoFoto = "è uma aparalho celular";
    public static final Double valor = 1000.00;
    public static final int ID = 1;
    public static final Enum Categoria = TECNOLOGIA;
    public static final int quantidade = 1;
    public static final String estado = "São Paulo";
    public static final String cidade = "São Paulo";
    public static final Enum entrega = TRANSPORTADORA;
    public static final Enum status = DISPONIVEL;


    @InjectMocks
    AnuncioService anuncioService;

    @MockBean
    IAnuncioRepository iAnuncioRepository;

    private AnuncioModel anuncioModel;

    private Optional<AnuncioModel> optionalUsuarioModel;

    @BeforeEach
    private void setAnuncioService() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void CadastrarAnuncio() {
        Mockito.when(iAnuncioRepository.save(anuncioModel)).thenReturn(anuncioModel);
        anuncioService.cadastrarNovoAnuncio(anuncioModel);
        Mockito.verify(iAnuncioRepository, Mockito.times(1)).save(anuncioModel);
    }
    @Test
    void BuscarTodosAnuncios() {
        List<AnuncioModel> listTroca = new ArrayList<>();
        Mockito.when(iAnuncioRepository.findAll()).thenReturn(listTroca);
        List<AnuncioModel> listTest = new ArrayList<>();
        listTest = anuncioService.buscarTodosAnuncios();
        Assertions.assertEquals(listTest, listTroca);

    }

    @Test
    void buscaIdAnuncio() {
        AnuncioModel anuncioModel = new AnuncioModel();
        anuncioModel.setId(1L);
        Mockito.when(iAnuncioRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(anuncioModel));
        var status = anuncioService.buscarPorId();
        Assertions.assertEquals(status.getId(),anuncioModel.getId());
    }

    @Test
    void alterarAnuncio() {
        Mockito.when(iAnuncioRepository.save(anuncioModel)).thenReturn(anuncioModel);
        anuncioService.alterarAnuncio(anuncioModel);
        Mockito.verify(iAnuncioRepository, Mockito.times(1)).save(anuncioModel);

    }

    @Test
    void deletarAnuncio() {
        anuncioService.deletarAnuncio(anuncioModel.getId());
        Mockito.verify(iAnuncioRepository,Mockito.times(1)).deleteById(anuncioModel.getId());
    }

}
