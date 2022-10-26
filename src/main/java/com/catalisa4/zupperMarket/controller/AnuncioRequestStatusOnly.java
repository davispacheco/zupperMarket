package com.catalisa4.zupperMarket.controller;

import com.catalisa4.zupperMarket.enums.Status;
import com.catalisa4.zupperMarket.model.AnuncioModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AnuncioRequestStatusOnly {
    private Status status;

    public AnuncioModel toAnuncioModel() {
        return new AnuncioModel(status);
    }
}
