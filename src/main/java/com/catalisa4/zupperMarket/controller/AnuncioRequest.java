package com.catalisa4.zupperMarket.controller;

import com.catalisa4.zupperMarket.enums.Categoria;
import com.catalisa4.zupperMarket.enums.FormaDeEntrega;
import com.catalisa4.zupperMarket.model.AnuncioModel;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
public class AnuncioRequest {

    @NotBlank(message = "Anúncio deve conter título.")
    @Length(max = 60, message = "Nome do título deve conter, no máximo, {max} caracteres.")
    private String nomeDoTitulo;

    @NotBlank(message = "Anúncio deve conter descrição.")
    @Length(max = 300, message = "Descrição deve conter, no máximo, {max} caracteres.")
    private String descricao;

    @Length(max = 800, message = "URL da foto deve conter, no máximo, {max} caracteres.")
    private String urlFoto;

    @Length(max = 300, message = "Descrição da foto deve conter, no máximo, {max} caracteres.")
    private String descricaoFoto;

    @NotNull(message = "Anúncio deve conter valor. Caso não tenha valor definido, coloque 0 (zero).")
    private double valor;

    @NotNull(message = "Defina se o valor é negociável ou não.")
    private boolean seNegociavel;

    @NotNull(message = "Informe a categoria do anúncio.")
    private Categoria categoria;

    @NotNull(message = "Insira a quantidade disponível.")
    private int quantidade;

    @NotBlank(message = "Insira o estado onde o objeto ou serviço está localizado.")
    @Length(max = 20, message = "Estado deve conter, no máximo, {max} caracteres.")
    private String estado;

    @NotBlank(message = "Insira a cidade onde o objeto ou serviço está localizado.")
    @Length(max = 30, message = "Cidade deve conter, no máximo, {max} caracteres.")
    private String cidade;

    @NotNull(message = "Informe qual a forma de entrega do produto ou serviço.")
    private FormaDeEntrega entrega;

    @NotNull(message = "Usuário deve ser preenchido.")
    private Long usuarioId;


    public AnuncioModel toAnuncioModel() {
        return new AnuncioModel(nomeDoTitulo, descricao, urlFoto, descricaoFoto, valor, seNegociavel, categoria,
                quantidade, estado, cidade, entrega);
    }

}
