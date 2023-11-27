package com.example.wktechnology.utils;

import com.example.wktechnology.model.entity.Pessoa;
import com.example.wktechnology.utils.enums.TipoSanguineo;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CompatibilidadeSanguinea {

    private final Map<TipoSanguineo, Map<String, List<TipoSanguineo>>> tabelaCompatibilidade = new HashMap<>();

    private final String doarPara = "doarPara";
    private final String receberDe = "receberDe";

    public CompatibilidadeSanguinea(){
        // Tipos sanguíneos que o A+ pode doar e receber
        Map<String, List<TipoSanguineo>> aPositivo = new HashMap<>();
        aPositivo.put(doarPara, Arrays.asList(TipoSanguineo.A_POSITIVO, TipoSanguineo.AB_POSITIVO));
        aPositivo.put(receberDe, Arrays.asList(TipoSanguineo.A_POSITIVO, TipoSanguineo.A_NEGATIVO, TipoSanguineo.O_POSITIVO, TipoSanguineo.O_NEGATIVO));
        tabelaCompatibilidade.put(TipoSanguineo.A_POSITIVO, aPositivo);

        // Tipos sanguíneos que o A- pode doar e receber
        Map<String, List<TipoSanguineo>> aNegativo = new HashMap<>();
        aNegativo.put(doarPara, Arrays.asList(TipoSanguineo.A_POSITIVO, TipoSanguineo.A_NEGATIVO, TipoSanguineo.AB_POSITIVO, TipoSanguineo.AB_NEGATIVO));
        aNegativo.put(receberDe, Arrays.asList(TipoSanguineo.A_NEGATIVO, TipoSanguineo.O_NEGATIVO));
        tabelaCompatibilidade.put(TipoSanguineo.A_NEGATIVO, aNegativo);


        Map<String, List<TipoSanguineo>> bPositivo = new HashMap<>();
        bPositivo.put(doarPara, Arrays.asList(TipoSanguineo.B_POSITIVO, TipoSanguineo.AB_POSITIVO));
        bPositivo.put(receberDe, Arrays.asList(TipoSanguineo.B_POSITIVO, TipoSanguineo.B_NEGATIVO, TipoSanguineo.O_POSITIVO, TipoSanguineo.O_NEGATIVO));
        tabelaCompatibilidade.put(TipoSanguineo.B_POSITIVO, bPositivo);

        Map<String, List<TipoSanguineo>> bNegativo = new HashMap<>();
        bNegativo.put(doarPara, Arrays.asList(TipoSanguineo.B_POSITIVO, TipoSanguineo.B_NEGATIVO,TipoSanguineo.AB_POSITIVO,TipoSanguineo.AB_NEGATIVO));
        bNegativo.put(receberDe, Arrays.asList(TipoSanguineo.B_NEGATIVO, TipoSanguineo.O_NEGATIVO));
        tabelaCompatibilidade.put(TipoSanguineo.B_NEGATIVO, bNegativo);

        Map<String, List<TipoSanguineo>> abPositivo = new HashMap<>();
        abPositivo.put(doarPara, Arrays.asList(TipoSanguineo.AB_POSITIVO));
        abPositivo.put(receberDe, Arrays.asList(TipoSanguineo.A_POSITIVO,TipoSanguineo.B_POSITIVO,TipoSanguineo.O_POSITIVO,TipoSanguineo.AB_POSITIVO,TipoSanguineo.A_NEGATIVO,TipoSanguineo.B_NEGATIVO,TipoSanguineo.O_NEGATIVO,TipoSanguineo.AB_NEGATIVO));
        tabelaCompatibilidade.put(TipoSanguineo.AB_POSITIVO, abPositivo);

        Map<String, List<TipoSanguineo>> abNegativo = new HashMap<>();
        abNegativo.put(doarPara, Arrays.asList(TipoSanguineo.AB_POSITIVO,TipoSanguineo.AB_NEGATIVO));
        abNegativo.put(receberDe, Arrays.asList(TipoSanguineo.A_NEGATIVO,TipoSanguineo.B_NEGATIVO,TipoSanguineo.O_NEGATIVO,TipoSanguineo.AB_NEGATIVO));
        tabelaCompatibilidade.put(TipoSanguineo.AB_NEGATIVO, abNegativo);


        Map<String, List<TipoSanguineo>> oPositivo = new HashMap<>();
        oPositivo.put(doarPara, Arrays.asList(TipoSanguineo.A_POSITIVO,TipoSanguineo.B_POSITIVO,TipoSanguineo.O_POSITIVO,TipoSanguineo.AB_POSITIVO));
        oPositivo.put(receberDe, Arrays.asList( TipoSanguineo.O_POSITIVO,TipoSanguineo.O_NEGATIVO ));
        tabelaCompatibilidade.put(TipoSanguineo.O_POSITIVO, oPositivo);

        Map<String, List<TipoSanguineo>> oNegativo = new HashMap<>();
        oNegativo.put(doarPara, Arrays.asList(TipoSanguineo.A_POSITIVO,TipoSanguineo.B_POSITIVO,TipoSanguineo.O_POSITIVO,TipoSanguineo.AB_POSITIVO,TipoSanguineo.A_NEGATIVO,TipoSanguineo.B_NEGATIVO,TipoSanguineo.O_NEGATIVO,TipoSanguineo.AB_NEGATIVO));
        oNegativo.put(receberDe, Arrays.asList(TipoSanguineo.O_NEGATIVO));
        tabelaCompatibilidade.put(TipoSanguineo.O_NEGATIVO, oNegativo);
    }


    public Map<TipoSanguineo, Integer> calcularDoadoresParaReceptor(Map<TipoSanguineo, List<Pessoa>> pessoasPorTipoSanguineo) {
        Map<TipoSanguineo, Integer> doadoresParaReceptor = new HashMap<>();

        for (TipoSanguineo receptor : tabelaCompatibilidade.keySet()) {
            int quantidadeDoadores = 0;

            List<TipoSanguineo> doadoresPermitidos = tabelaCompatibilidade.get(receptor).get(receberDe);
            for (TipoSanguineo doadorPermitido : doadoresPermitidos) {
                List<Pessoa> pessoasDoadoras = pessoasPorTipoSanguineo.getOrDefault(doadorPermitido, new ArrayList<>());
                quantidadeDoadores += pessoasDoadoras.size();
            }
            doadoresParaReceptor.put(receptor, quantidadeDoadores);
        }
        return doadoresParaReceptor;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<TipoSanguineo, Map<String, List<TipoSanguineo>>> entry : tabelaCompatibilidade.entrySet()) {
            TipoSanguineo tipo = entry.getKey();
            Map<String, List<TipoSanguineo>> dadosTipo = entry.getValue();

            stringBuilder.append("Tipo Sanguíneo: ").append(tipo).append("\n");

            List<TipoSanguineo> podeDoarPara = dadosTipo.get(doarPara);
            List<TipoSanguineo> podeReceberDe = dadosTipo.get(receberDe);

            stringBuilder.append("  Pode Doar Para: ").append(podeDoarPara).append("\n");
            stringBuilder.append("  Pode Receber De: ").append(podeReceberDe).append("\n\n");
        }

        return stringBuilder.toString();
    }
}
