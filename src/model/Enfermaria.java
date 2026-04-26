package model;

import interfaces.Indicadores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
/**
 * Classe abstrata que representa uma enfermaria.
 */

public abstract class Enfermaria implements Indicadores {

    protected String id;
    protected int camas;
    protected ArrayList<Episodio> episodios;
    /**
     * Cria uma enfermaria.
     *
     * @param id identificador
     * @param camas número de camas
     */


    public Enfermaria(String id, int camas) {
        this.id = id;
        this.camas = camas;
        this.episodios = new ArrayList<>();
    }
    /**
     * Devolve o identificador.
     *
     * @return id da enfermaria
     */


    public String getId() {
        return id;
    }
     /**
     * Adiciona um episódio à enfermaria.
     *
     * @param e episódio a adicionar
     */


    public void addEpisodio(Episodio e) {
        episodios.add(e);
    }
    /**
     * Calcula camas ocupadas numa data.
     *
     * @param data data de referência
     * @return número de camas ocupadas
     */


    public int ocupadas(LocalDate data) {
        int total = 0;

        for (Episodio e : episodios)
            if (e.ativo(data))
                total++;

        return total;
    }
    /**
     * Calcula taxa de ocupação.
     *
     * @param data data de referência
     * @return taxa em percentagem
     */
    
    public double taxaOcupacao(LocalDate data) {
        return ocupadas(data) * 100.0 / camas;
    }
    /**
     * Verifica estado de pressão.
     *
     * @param data data analisada
     * @return true se ocupação > 85%
     */

    public boolean emPressao(LocalDate data) {
        return taxaOcupacao(data) > 85;
    }
    /**
     * Calcula média do LoS.
     *
     * @return média dos episódios com alta
     */


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
    /**
     * Calcula menor LoS.
     *
     * @return valor mínimo
     */


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
    /**
     * Calcula maior LoS.
     *
     * @return valor máximo
     */


    public long maxLoS() {
        long max = 0;

        for (Episodio e : episodios)
            if (e.temAlta())
                max = Math.max(max, e.getLoS());

        return max;
    }
    /**
     * Calcula desvio padrão do LoS.
     *
     * @return desvio padrão
     */


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
    /**
     * Ordena episódios por data de admissão.
     */


    public void ordenarEpisodios() {
        episodios.sort(Comparator.comparing(Episodio::getAdmissao));
    }
    /**
     * Calcula percentagem de dias em pressão num intervalo.
     *
     * @param inicio data inicial
     * @param fim data final
     * @return percentagem de dias em pressão
     */


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
    /**
     * Apresenta o estado da enfermaria para cada dia de um intervalo.
     *
     * @param inicio data inicial
     * @param fim data final
     */


    public void estadoPorDia(LocalDate inicio, LocalDate fim) {

        for (LocalDate d = inicio; !d.isAfter(fim); d = d.plusDays(1)) {

            if (taxaOcupacao(d) > 85)
                System.out.println(d + " -> EM PRESSÃO");
            else
                System.out.println(d + " -> NORMAL");
        }
    }

}
