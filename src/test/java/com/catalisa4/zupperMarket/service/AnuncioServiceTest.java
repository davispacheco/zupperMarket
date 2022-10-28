//package com.catalisa4.zupperMarket.service;
//
//import com.catalisa4.zupperMarket.enums.Categoria;
//import com.catalisa4.zupperMarket.enums.FormaDeEntrega;
//import com.catalisa4.zupperMarket.enums.Status;
//import com.catalisa4.zupperMarket.model.AnuncioModel;
//
//import com.catalisa4.zupperMarket.model.UsuarioModel;
//import com.catalisa4.zupperMarket.repository.IAnuncioRepository;
//import org.junit.jupiter.api.Assertions;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static com.catalisa4.zupperMarket.enums.Categoria.TECNOLOGIA;
//import static com.catalisa4.zupperMarket.enums.FormaDeEntrega.TRANSPORTADORA;
//import static com.catalisa4.zupperMarket.enums.Status.DISPONIVEL;
//
//
//public class AnuncioServiceTest {
//
//
//    public static final String nomeDoTitulo = "Katia Queiroz";
//    public static final String descricao = "Esse celular é muito bom ";
//    public static final String urlFoto = "https://www.apple.com/br/shop/buy-iphone/iphone-14-pro";
//    public static final String descricaoFoto = "è uma aparalho celular";
//    public static final Double valor = 1000.00;
//    public static final Long ID = 1L;
//    public static final Categoria categoria = TECNOLOGIA;
//    public static final int quantidade = 1;
//    public static final String estado = "São Paulo";
//    public static final String cidade = "São Paulo";
//    public static final FormaDeEntrega entrega = TRANSPORTADORA;
//    public static final Status status = DISPONIVEL;
//
//
//
//    @Mock
//    IAnuncioRepository iAnuncioRepository;
//
//    @InjectMocks
//    AnuncioService anuncioService;
//
//    private AnuncioModel anuncioModel;
//
//    private Optional<AnuncioModel> optionalUsuarioModel;
//
//
//    @BeforeEach
//    private void setAnuncioService() {
//        anuncioModel = new AnuncioModel("Titulo", "esse é um bla", "http://celular.com", "tem um celular",
//                1000, true, TECNOLOGIA,
//                1, "PE", "PETROLINA", TRANSPORTADORA); new UsuarioModel("Vitoria", "vi",
//                "vitoria@zup.com", "8788586589", "12345678");
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void cadastrarAnuncio() {
//        Mockito.when(iAnuncioRepository.save(Mockito.any())).thenReturn(anuncioModel);
//        anuncioService.cadastrarNovoAnuncio(anuncioModel, anuncioModel.getId());
//        Mockito.verify(iAnuncioRepository, Mockito.times(1)).save(Mockito.any());
//    }
//
//    @Test
//    void BuscarTodosAnuncios() {
//        List<AnuncioModel> listTroca = new ArrayList<>();
//        Mockito.when(iAnuncioRepository.findAll()).thenReturn(listTroca);
//        List<AnuncioModel> listTest = new ArrayList<>();
//        listTest = anuncioService.buscarTodosAnuncios();
//        Assertions.assertEquals(listTest, listTroca);
//
//    }
//
//    @Test
//    void buscaIdAnuncio() {
//        AnuncioModel anuncioModel = new AnuncioModel();
//        anuncioModel.setId(1L);
//        Mockito.when(iAnuncioRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(anuncioModel));
//        var status = anuncioService.buscarPorId(ID);
//        Assertions.assertEquals(status.getId(), anuncioModel.getId());
//    }
//
//    @Test
//    void alterarAnuncio() {
//        Mockito.when(iAnuncioRepository.save(anuncioModel)).thenReturn(anuncioModel);
//        anuncioService.alterarAnuncio(anuncioModel);
//        Mockito.verify(iAnuncioRepository, Mockito.times(1)).save(Mockito.any());
//
//    }
//
//    @Test
//    void deletarAnuncio() {
//        anuncioService.deletarAnuncio(anuncioModel.getId());
//        Mockito.verify(iAnuncioRepository, Mockito.times(1)).deleteById(anuncioModel.getId());
//    }
//}
//
