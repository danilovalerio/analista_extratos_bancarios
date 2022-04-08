package com.danilo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    //public void analyze(final String fileName, Final BankStatementParser bankStatementParser);

    public static void main(final String...args) throws IOException {
        final BankStatementCSVParser bankStatementCSVParser = new BankStatementCSVParser();
        final String fileName = "dados.csv";
        final String RESOURCES = "src/resources/";
        final Path path = Paths.get(RESOURCES + "dados.csv");
        final List<String> lines = Files.readAllLines(path);
        final List<BankTransaction> bankTransactions = bankStatementCSVParser.parseLinesFrom(lines);
        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);
        collectSummary(bankStatementProcessor);
    }

    private static void collectSummary(final BankStatementProcessor bankStatementProcessor) {

        System.out.println("O total para todas transações: "+ String.format("%.2f", bankStatementProcessor.calculateTotalAmount()));
        System.out.println("Transações no mês de Janeiro: " + bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));
        System.out.println("Transações no mês de Fevereiro: " + bankStatementProcessor.calculateTotalInMonth(Month.FEBRUARY));
        System.out.println("Total de salário foi: " + bankStatementProcessor.calculateTotalForCategory("Salary"));
    }
}