package com.catalisa4.zupperMarket.testService;

import com.catalisa4.zupperMarket.model.UsuarioModel;
import com.catalisa4.zupperMarket.repository.IUsuarioRepository;
import com.catalisa4.zupperMarket.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UsuarioTest {

    @Mock
    UsuarioService usuarioService;

    @MockBean
    IUsuarioRepository iUsuarioRepository;

    private UsuarioModel usuarioModel;

    @BeforeEach
    private void inicializador() {
        MockitoAnnotations.openMocks(this);
        usuarioModel = new UsuarioModel();
    }

    @Test
    @DisplayName("Testando o metodo de cadastrar")
    void testeSeCadastroDeUsuarioEstaFuncionando(){
        iUsuarioRepository.save(usuarioModel);
        when(iUsuarioRepository.existsBYEmail(Mockito.anyString())).thenReturn(true);
        Mockito.verify(iUsuarioRepository, Mockito.times(1)).save(usuarioModel);
    }

}
