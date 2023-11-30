package br.com.projeto_funcionario.principal;

import br.com.projeto_funcionario.classes.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

        // 3.1 - Inserir funcionários
        funcionarios.add(new Funcionario("Maria", "18/10/2000", "2009.44", "Operador"));
        funcionarios.add(new Funcionario("João", "12/05/1990", "2284.38", "Operador"));
        funcionarios.add(new Funcionario("Caio", "02/05/1961", "9836.14", "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", "14/10/1988", "19119.88", "Diretor"));
        funcionarios.add(new Funcionario("Alice", "05/01/1995", "2234.68", "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", "19/11/1999", "1582.72", "Operador"));
        funcionarios.add(new Funcionario("Arthur", "31/03/1993", "4071.84", "Contador"));
        funcionarios.add(new Funcionario("Laura", "08/07/1994", "3017.45", "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", "24/05/2003", "1606.85", "Eletricista"));
        funcionarios.add(new Funcionario("Helena", "02/09/1996", "2799.93", "Gerente"));

        // 3.2 - Remover o funcionário "João"
        funcionarios.removeIf(funcionario -> funcionario.nome.equals("João"));

        // 3.3 - Imprimir todos os funcionários
        System.out.println("Funcionários:");
        funcionarios.forEach(System.out::println);

        // 3.4 - Aumentar salários em 10%
        funcionarios.forEach(funcionario -> funcionario.aplicarAumento(new BigDecimal("0.10")));
        System.out.println("\nFuncionários com salários reajustados:");
        funcionarios.forEach(System.out::println);

        // 3.5 - Agrupar por função
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 - Imprimir funcionários agrupados por função
        System.out.println("\nFuncionários Agrupados por Função:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(System.out::println);
            System.out.println();
        });

        // 3.8 - Imprimir funcionários que fazem aniversário em outubro (mês 10) e dezembro (mês 12)
        System.out.println("\nFuncionários que fazem aniversário em outubro e dezembro:");
        funcionarios.stream()
                .filter(funcionario -> funcionario.dataNascimento.getMonthValue() == 10 ||
                        funcionario.dataNascimento.getMonthValue() == 12)
                .forEach(System.out::println);

        // 3.9 - Imprimir funcionário mais velho
        String nomeMaisVelho = "";
        int maiorIdade = 0; // Assumindo a primeira idade como a maior

        for (Funcionario funcionario : funcionarios) {
            String nomeFuncionario = funcionario.nome;
            int idade = Period.between(funcionario.dataNascimento, LocalDate.now()).getYears();
            if (idade > maiorIdade) {
                maiorIdade = idade;
                nomeMaisVelho = nomeFuncionario;
            }
        }

        System.out.println("\nImprimindo o funcionário mais velho: ");
        System.out.println("Nome: " + nomeMaisVelho + " Idade: " + maiorIdade);

        // 3.10 - Imprimir lista de funcionários por ordem alfabética
        System.out.println("\nFuncionários em Ordem Alfabética:");
        funcionarios.stream()
                .sorted(Comparator.comparing(funcionario -> funcionario.nome))
                .forEach(System.out::println);

        // 3.11 - Imprimir o total dos salários dos funcionários
        System.out.println("\nTotal dos Salários dos Funcionários: " +
                DecimalFormat.getCurrencyInstance().format(
                        funcionarios.stream()
                                .map(Funcionario::getSalario)
                                .reduce(BigDecimal.ZERO, BigDecimal::add)
                )
        );

        // 3.12 - Imprimir quantos salários mínimos ganha cada funcionário
        System.out.println("\nSalários em Múltiplos de Salário Mínimo:");
        BigDecimal salarioMinimo = new BigDecimal("1320.00");
        funcionarios.forEach(funcionario ->
                System.out.println(funcionario.nome + ": " +
                        funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP))
        );
    }
}
