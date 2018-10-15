/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.excel.controller;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author paulo.pereira
 * @param <T>
 */
public class EscreverArquivo<T> {
   
    private T t;

    private int linha;
    private int coluna;

    public EscreverArquivo(T t) {
        this.t = t;
        linha = 0;
        coluna = 0;
    }

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

    private void escreverHeader(Row headerRow) {
        int i = 0;
        for (String nomeColuna : obterNomesDasColunas(t.getClass())) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(nomeColuna);
            i++;
        }
    }

    private void escreverCelulas(List<T> objects, Sheet sheet) throws IllegalArgumentException, IllegalAccessException {
        for (Object object : objects) {
            Row row = sheet.createRow(linha++);
            Class clazzItem = object.getClass();
            coluna = 0;

            obterValorAteTipoPrimitivo(clazzItem, object, row);
        }
    }

    private void obterValorAteTipoPrimitivo(Class clazzItem, Object object, Row row) throws IllegalArgumentException, IllegalAccessException {
        for (Field field : clazzItem.getDeclaredFields()) {
            if (field.getType().isPrimitive() || field.getType().equals(String.class)) {
                if (field.get(object) != null) {
                    row.createCell(coluna).setCellValue(field.get(object).toString());
                } else {
                    row.createCell(coluna).setCellValue("");
                }
                coluna++;

            } else {
                obterValorAteTipoPrimitivo(field.getType(), field.get(object), row);
            }
        }
    }

    private void atribuirTamanhoDaColunaPeloHeader(Sheet sheet) {
        for (int i = 0; i < obterNomesDasColunas(t.getClass()).size(); i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private void escreverArquivo(Workbook workbook) {
        try {
            FileOutputStream fileOut = new FileOutputStream(t.getClass().getSimpleName());
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            System.out.println("Erro" + e);
        }
    }

    public void escreverArquivoXLS(List<T> objects) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(t.getClass().getSimpleName());

        escreverHeader(sheet.createRow(linha));

        linha = 1;

        escreverCelulas(objects, sheet);

        atribuirTamanhoDaColunaPeloHeader(sheet);

        escreverArquivo(workbook);
    }
}
