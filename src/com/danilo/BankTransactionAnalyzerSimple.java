package com.danilo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/*
Consultas desejadas pelo cliente
Ele gostaria de ter respostas para as seguintes consultas: 
 - Qual o total de lucros e perdas em uma lista de extratos bancários?
 - É positivo ou negativo? Quantas transações bancárias existem em um mês específico?
 - Quais são suas 10 principais despesas?
 - Em qual categoria ele mais gasta dinheiro?
* */

public class BankTransactionAnalyzerSimple {
    private static final String RESOURCES = "src/resources/";

    public static void main(final String...args) throws IOException {
        final Path path = Paths.get(RESOURCES + "dados.csv");
        final List<String> lines = Files.readAllLines(path);
        double total = 0d;

        for (final  String line: lines) {
            final String[] columns = line.split(",");
            final double amount = Double.parseDouble(columns[1]);
            total += amount;
        }

        System.out.println("O total para todas transacoes é " + total);
    }
}
