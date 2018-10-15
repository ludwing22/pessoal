/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.excel.model;

/**
 *
 * @author paulo.pereira
 */
public class Endereco {
    public String nomeRua;
    public String numero;
    public String bairro;

    public Endereco() {
    }

    public Endereco(String nomeRua, String numero, String bairro) {
        this.nomeRua = nomeRua;
        this.numero = numero;
        this.bairro = bairro;
    }
    
    public String getNomeRua() {
        return nomeRua;
    }

    public void setNomeRua(String nomeRua) {
        this.nomeRua = nomeRua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    
    
    
}
