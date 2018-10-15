/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.excel.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author paulo.pereira
 * @param <T>
 */
public class EscreverArquivoCSV<T> {
    
    private T t;

    public EscreverArquivoCSV(T t) {
        this.t = t;
    }
    
    private String linha = "";

    private List<String> obterNomesDasColunas(Class clazz) {
        List<String> colunas = new ArrayList<>();
        obterNomeAteTipoPrimitivo(colunas, clazz);
        return colunas;
    }

    private void obterNomeAteTipoPrimitivo(List<String> colunas, Class clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getType().isPrimitive() || field.getType().equals(String.class)) {
                colunas.add(field.getName());
            } else {
                obterNomeAteTipoPrimitivo(colunas, field.getType());
            }
        }
    }

    private void escreverHeader(PrintWriter dadoArquivo) {
        linha = "";
        for (String nomeColuna : obterNomesDasColunas(t.getClass())) {
            linha += nomeColuna + ";";
        }
        linha += "%n";
        dadoArquivo.printf(linha);
    }

    private void escreverCelulas(List<T> objects, PrintWriter dadoArquivo) throws IllegalArgumentException, IllegalAccessException {
        for (Object object : objects) {
            linha = "";
            Class clazzItem = object.getClass();
            obterValorAteTipoPrimitivo(clazzItem, object, dadoArquivo);
            linha += "%n";
            dadoArquivo.printf(linha);
        }
    }

    private void obterValorAteTipoPrimitivo(Class clazzItem, Object object, PrintWriter dadoArquivo) throws IllegalArgumentException, IllegalAccessException {
        for (Field field : clazzItem.getDeclaredFields()) {
            if (field.getType().isPrimitive() || field.getType().equals(String.class)) {
                if (field.get(object) != null) {
                    linha += field.get(object) + ";";
                } else {
                    linha += ";";
                }
            } else {
                obterValorAteTipoPrimitivo(field.getType(), field.get(object), dadoArquivo);
            }
        }
    }

    public void escrever(List<T> objects) throws IOException, IllegalArgumentException, IllegalAccessException {
        String nomeArquivo = t.getClass().getSimpleName() + ".csv";
        try (FileWriter arquivoCsv = new FileWriter(nomeArquivo)) {
            PrintWriter dadoArquivo = new PrintWriter(arquivoCsv);

            escreverHeader(dadoArquivo);

            escreverCelulas(objects, dadoArquivo);
        } catch (Exception e) {
            System.out.println("Deu ruim" + e);
        }

    }

}
