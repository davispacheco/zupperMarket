package com.catalisa4.zupperMarket.DTO;

import com.catalisa4.zupperMarket.model.UsuarioModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioRequest {
    @NotBlank(message = "Nome completo deve ser preenchido.")
    @Length(max = 60, message = "Nome completo deve conter, no máximo, {max} caracteres.")
    private String nomeCompleto;

    @NotBlank(message = "Apelido deve ser preenchido.")
    @Length(max = 60, message = "Apelido deve conter, no máximo, {max} caracteres.")
    private String apelido;

    @Length(max = 60, message = "Celular deve conter, no máximo, {max} caracteres.")
    private String celular;

    @NotBlank(message = "Senha deve ser preenchida.")
    @Length(min = 8, max = 60, message = "Senha deve conter, no mínimo, {min} e, no máximo, {max} caracteres.")
    private String senha;

    public UsuarioModel converterParaObjeto() {
        return new UsuarioModel(nomeCompleto, apelido, celular, senha);
    }
}
