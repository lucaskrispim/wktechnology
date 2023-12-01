package com.example.wktechnology.domain.indicadores;

public class Imc extends Indicador {
    private double altura;
    private double peso;

    public Imc(double peso, double altura){

        if (altura <= 0 || peso <= 0) {
            throw new IllegalArgumentException("Altura e peso devem ser maiores que zero.");
        }

        this.altura = altura;
        this.peso = peso;
    }

    public double calcular() {
        return Math.round( ( peso / (altura * altura) ) * 100.0) / 100.0;
    }

    public String classificarIMC() {
        double imc = calcular();

        if (imc < 16) {
            return "Magreza grave";
        } else if (imc >= 16 && imc < 16.9) {
            return "Magreza moderada";
        } else if (imc >= 17 && imc < 18.4) {
            return "Magreza leve";
        } else if (imc >= 18.5 && imc < 24.9) {
            return "Peso saudável";
        } else if (imc >= 25 && imc < 29.9) {
            return "Sobrepeso";
        } else if (imc >= 30 && imc < 34.9) {
            return "Obesidade grau I";
        } else if (imc >= 35 && imc < 39.9) {
            return "Obesidade grau II (severa)";
        } else if (imc >= 40 && imc < 49.9) {
            return "Obesidade grau III (mórbida)";
        } else if (imc >= 50 && imc < 59.9) {
            return "Obesidade grau IV (extrema)";
        } else if (imc >= 60) {
            return "Obesidade grau V (supermórbida)";
        } else {
            return "Valor de IMC inválido";
        }
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}

