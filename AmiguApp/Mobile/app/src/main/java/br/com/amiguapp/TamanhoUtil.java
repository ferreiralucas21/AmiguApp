package br.com.amiguapp;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class TamanhoUtil {
    public static String tamanhoFormat(Float valor){
        NumberFormat formatoCentimetro = DecimalFormat.getNumberInstance(new Locale("pt", "br"));
        String tamanho = formatoCentimetro.format(valor);
        return tamanho;
    }
}
