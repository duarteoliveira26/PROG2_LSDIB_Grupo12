package model;

import interfaces.Indicadores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public abstract class Enfermaria implements Indicadores {

    protected String id;
    protected int camas;
    protected ArrayList<Episodio> episodios;


    public Enfermaria(String id, int camas) {
        this.id = id;
        this.camas = camas;
        this.episodios = new ArrayList<>();
    }


    public String getId() {
        return id;
    }


    public void addEpisodio(Episodio e) {
        episodios.add(e);
    }


    public int ocupadas(LocalDate data) {
        int total = 0;

        for (Episodio e : episodios)
            if (e.ativo(data))
                total++;

        return total;
    }


    public double taxaOcupacao(LocalDate data) {
        return ocupadas(data) * 100.0 / camas;
    }


    public boolean emPressao(LocalDate data) {
        return taxaOcupacao(data) > 85;
    }


    public double mediaLoS() {
        double soma = 0;
        int n = 0;

        for (Episodio e : episodios) {
            if (e.temAlta()) {
                soma += e.getLoS();
                n++;
            }
        }

        return (n == 0) ? 0 : soma / n;
    }


    public long minLoS() {
        long min = Long.MAX_VALUE;
        boolean existe = false;

        for (Episodio e : episodios) {
            if (e.temAlta()) {
                min = Math.min(min, e.getLoS());
                existe = true;
            }
        }

        return existe ? min : 0;
    }


    public long maxLoS() {
        long max = 0;

        for (Episodio e : episodios)
            if (e.temAlta())
                max = Math.max(max, e.getLoS());

        return max;
    }


    public double desvioPadrao() {
        double media = mediaLoS();
        double soma = 0;
        int n = 0;

        for (Episodio e : episodios) {
            if (e.temAlta()) {
                soma += Math.pow(e.getLoS() - media, 2);
                n++;
            }
        }

        return (n == 0) ? 0 : Math.sqrt(soma / n);
    }


    public void ordenarEpisodios() {
        episodios.sort(Comparator.comparing(Episodio::getAdmissao));
    }


    public double percentagemPressao(LocalDate inicio, LocalDate fim) {

        int total = 0;
        int pressao = 0;

        for (LocalDate d = inicio; !d.isAfter(fim); d = d.plusDays(1)) {
            total++;

            if (taxaOcupacao(d) > 85)
                pressao++;
        }

        return (total == 0) ? 0 : (pressao * 100.0 / total);
    }


    public void estadoPorDia(LocalDate inicio, LocalDate fim) {

        for (LocalDate d = inicio; !d.isAfter(fim); d = d.plusDays(1)) {

            if (taxaOcupacao(d) > 85)
                System.out.println(d + " -> EM PRESSÃO");
            else
                System.out.println(d + " -> NORMAL");
        }
    }

}