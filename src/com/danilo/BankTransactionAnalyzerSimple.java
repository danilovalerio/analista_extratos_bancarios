package com.danilo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

/*
Consultas desejadas pelo cliente
Ele gostaria de ter respostas para as seguintes consultas: 
 - Qual o total de lucros e perdas em uma lista de extratos bancários?
 - É positivo ou negativo?
 - Quantas transações bancárias existem em um mês específico?
 - Quais são suas 10 principais despesas?
 - Em qual categoria ele mais gasta dinheiro?
* */

public class BankTransactionAnalyzerSimple {
    private static final String RESOURCES = "src/resources/";

    public static void main(final String...args) throws IOException {
        final Path path = Paths.get(RESOURCES + "dados.csv");
        final List<String> lines = Files.readAllLines(path);
        double total = 0d;

        /*
         * Calculo do total das transações
         * */
        for (final  String line: lines) {
            final String[] columns = line.split(",");
            final double amount = Double.parseDouble(columns[1]);
            total += amount;
        }
        System.out.println("O total para todas transações: "+ String.format("%.2f", total));

        /*
         * Valor total de transações por mês específico
         */
        double totalPorMes = 0d;
        final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (final String line: lines) {
            final String[] columns = line.split(",");
            final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
            if (date.getMonth() == Month.JANUARY){
                final double amount = Double.parseDouble(columns[1]);
                totalPorMes += amount;
            }
        }
        System.out.println("O total para mês de Janeiro: " + String.format("%.2f", totalPorMes));
    }
}
