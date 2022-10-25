package com.catalisa4.zupperMarket.controller;

import com.catalisa4.zupperMarket.enums.Categoria;
import com.catalisa4.zupperMarket.enums.FormaDeEntrega;
import com.catalisa4.zupperMarket.model.AnuncioModel;
import lombok.*;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
@Setter
public class AnuncioRequest {

    @NotBlank(message = "Anúncio deve conter título.")
    private String nomeDoTitulo;

    @NotBlank(message = "Anúncio deve conter descrição.")
    private String descricao;

    private String urlFoto;

    private String descricaoFoto;

    @NotBlank(message = "Anúncio deve conter valor. Caso não tenha valor definido, coloque 0 (zero).")
    private double valor;

    @NotBlank(message = "Defina se o valor é negociável ou não.")
    private boolean seNegociavel;

    @NotBlank(message = "Informe a categoria do anúncio.")
    private Categoria categoria;

    @NotBlank(message = "Insira a quantidade disponível.")
    private int quantidade;

    @NotBlank(message = "Insira o estado onde o objeto ou serviço está localizado.")
    private String estado;

    @NotBlank(message = "Insira a cidade onde o objeto ou serviço está localizado.")
    private String cidade;

    @NotBlank(message = "Informe qual a forma de entrega do produto ou serviço.")
    private FormaDeEntrega entrega;

    @NotBlank(message = "Usuário deve ser preenchido.")
    private Long usuarioId;

    public AnuncioModel toAnuncioModel() {
        return new AnuncioModel(nomeDoTitulo, descricao, urlFoto, descricaoFoto, valor, seNegociavel, categoria,
                quantidade, estado, cidade, entrega);
    }

}
