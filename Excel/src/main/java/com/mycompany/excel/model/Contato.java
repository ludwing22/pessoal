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
public class Contato extends Object{
    public String primeiroNome;
    public String segundoNome;
    public String telefone;
    public Endereco endereco;

    
    
    

    public Contato(String primeiroNome, String segundoNome, String telefone, Endereco endereco) {
        this.primeiroNome = primeiroNome;
        this.segundoNome = segundoNome;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public Contato() {
    }
    
    

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getSegundoNome() {
        return segundoNome;
    }

    public void setSegundoNome(String segundoNome) {
        this.segundoNome = segundoNome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
