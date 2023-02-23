package br.com.amiguapp;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class PrecoUtil {

    public static String precoFormat(Float valor){
        NumberFormat formatoBrasileiro = DecimalFormat.getCurrencyInstance(new Locale("pt", "br"));
        String moedaBrasileira = formatoBrasileiro.format(valor);
        return moedaBrasileira;
    }
}
