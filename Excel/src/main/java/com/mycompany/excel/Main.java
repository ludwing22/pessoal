/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.excel;

import com.mycompany.excel.controller.EscreverArquivo;
import com.mycompany.excel.controller.EscreverArquivoCSV;
import com.mycompany.excel.model.Contato;
import com.mycompany.excel.model.Endereco;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author paulo.pereira
 */
public class Main {
    private static List<Contato> contatos = new ArrayList<>();
    
    
    
    public static void main (String[] args) throws Exception{
        
        
        
        contatos.add(new Contato("Antonio", "Jose", "333333", new Endereco("Rua 1", "1", "Bairro1")));
        contatos.add(new Contato("Mario", "Luigi", "22222", new Endereco()));
        contatos.add(new Contato("Carlos", null, "111111", new Endereco("Rua 3", "3", "Bairro3")));
        
        
        EscreverArquivo<Contato> arquivoXLS = new EscreverArquivo<>(new Contato());
        EscreverArquivoCSV<Contato> arquivoCSV = new EscreverArquivoCSV<>(new Contato());
        arquivoXLS.escreverArquivoXLS(contatos);
        arquivoCSV.escrever(contatos);
        
    }  
}
