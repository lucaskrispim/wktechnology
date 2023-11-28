package com.example.wktechnology.utils;

import com.example.wktechnology.model.entity.Pessoa;
import com.example.wktechnology.utils.enums.TipoSanguineo;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CompatibilidadeSanguinea {

    private final Map<String, Map<String, List<String>>> tabelaCompatibilidade = new HashMap<>();

    private final String doarPara = "doarPara";
    private final String receberDe = "receberDe";

    public CompatibilidadeSanguinea(){
        // Tipos sanguíneos que o A+ pode doar e receber
        Map<String, List<String>> aPositivo = new HashMap<>();
        aPositivo.put(doarPara, Arrays.asList(TipoSanguineo.A_POSITIVO.getDescricao(), TipoSanguineo.AB_POSITIVO.getDescricao()));
        aPositivo.put(receberDe, Arrays.asList(TipoSanguineo.A_POSITIVO.getDescricao(), TipoSanguineo.A_NEGATIVO.getDescricao(), TipoSanguineo.O_POSITIVO.getDescricao(), TipoSanguineo.O_NEGATIVO.getDescricao()));
        tabelaCompatibilidade.put(TipoSanguineo.A_POSITIVO.getDescricao(), aPositivo);

        // Tipos sanguíneos que o A- pode doar e receber
        Map<String, List<String>> aNegativo = new HashMap<>();
        aNegativo.put(doarPara, Arrays.asList(TipoSanguineo.A_POSITIVO.getDescricao(), TipoSanguineo.A_NEGATIVO.getDescricao(), TipoSanguineo.AB_POSITIVO.getDescricao(), TipoSanguineo.AB_NEGATIVO.getDescricao()));
        aNegativo.put(receberDe, Arrays.asList(TipoSanguineo.A_NEGATIVO.getDescricao(), TipoSanguineo.O_NEGATIVO.getDescricao()));
        tabelaCompatibilidade.put(TipoSanguineo.A_NEGATIVO.getDescricao(), aNegativo);


        Map<String, List<String>> bPositivo = new HashMap<>();
        bPositivo.put(doarPara, Arrays.asList(TipoSanguineo.B_POSITIVO.getDescricao(), TipoSanguineo.AB_POSITIVO.getDescricao()));
        bPositivo.put(receberDe, Arrays.asList(TipoSanguineo.B_POSITIVO.getDescricao(), TipoSanguineo.B_NEGATIVO.getDescricao(), TipoSanguineo.O_POSITIVO.getDescricao(), TipoSanguineo.O_NEGATIVO.getDescricao()));
        tabelaCompatibilidade.put(TipoSanguineo.B_POSITIVO.getDescricao(), bPositivo);

        Map<String, List<String>> bNegativo = new HashMap<>();
        bNegativo.put(doarPara, Arrays.asList(TipoSanguineo.B_POSITIVO.getDescricao(), TipoSanguineo.B_NEGATIVO.getDescricao(),TipoSanguineo.AB_POSITIVO.getDescricao(),TipoSanguineo.AB_NEGATIVO.getDescricao()));
        bNegativo.put(receberDe, Arrays.asList(TipoSanguineo.B_NEGATIVO.getDescricao(), TipoSanguineo.O_NEGATIVO.getDescricao()));
        tabelaCompatibilidade.put(TipoSanguineo.B_NEGATIVO.getDescricao(), bNegativo);

        Map<String, List<String>> abPositivo = new HashMap<>();
        abPositivo.put(doarPara, Arrays.asList(TipoSanguineo.AB_POSITIVO.getDescricao()));
        abPositivo.put(receberDe, Arrays.asList(TipoSanguineo.A_POSITIVO.getDescricao(),TipoSanguineo.B_POSITIVO.getDescricao(),TipoSanguineo.O_POSITIVO.getDescricao(),TipoSanguineo.AB_POSITIVO.getDescricao(),TipoSanguineo.A_NEGATIVO.getDescricao(),TipoSanguineo.B_NEGATIVO.getDescricao(),TipoSanguineo.O_NEGATIVO.getDescricao(),TipoSanguineo.AB_NEGATIVO.getDescricao()));
        tabelaCompatibilidade.put(TipoSanguineo.AB_POSITIVO.getDescricao(), abPositivo);

        Map<String, List<String>> abNegativo = new HashMap<>();
        abNegativo.put(doarPara, Arrays.asList(TipoSanguineo.AB_POSITIVO.getDescricao(),TipoSanguineo.AB_NEGATIVO.getDescricao()));
        abNegativo.put(receberDe, Arrays.asList(TipoSanguineo.A_NEGATIVO.getDescricao(),TipoSanguineo.B_NEGATIVO.getDescricao(),TipoSanguineo.O_NEGATIVO.getDescricao(),TipoSanguineo.AB_NEGATIVO.getDescricao()));
        tabelaCompatibilidade.put(TipoSanguineo.AB_NEGATIVO.getDescricao(), abNegativo);


        Map<String, List<String>> oPositivo = new HashMap<>();
        oPositivo.put(doarPara, Arrays.asList(TipoSanguineo.A_POSITIVO.getDescricao(),TipoSanguineo.B_POSITIVO.getDescricao(),TipoSanguineo.O_POSITIVO.getDescricao(),TipoSanguineo.AB_POSITIVO.getDescricao()));
        oPositivo.put(receberDe, Arrays.asList( TipoSanguineo.O_POSITIVO.getDescricao(),TipoSanguineo.O_NEGATIVO.getDescricao() ));
        tabelaCompatibilidade.put(TipoSanguineo.O_POSITIVO.getDescricao(), oPositivo);

        Map<String, List<String>> oNegativo = new HashMap<>();
        oNegativo.put(doarPara, Arrays.asList(TipoSanguineo.A_POSITIVO.getDescricao(),TipoSanguineo.B_POSITIVO.getDescricao(),TipoSanguineo.O_POSITIVO.getDescricao(),TipoSanguineo.AB_POSITIVO.getDescricao(),TipoSanguineo.A_NEGATIVO.getDescricao(),TipoSanguineo.B_NEGATIVO.getDescricao(),TipoSanguineo.O_NEGATIVO.getDescricao(),TipoSanguineo.AB_NEGATIVO.getDescricao()));
        oNegativo.put(receberDe, Arrays.asList(TipoSanguineo.O_NEGATIVO.getDescricao()));
        tabelaCompatibilidade.put(TipoSanguineo.O_NEGATIVO.getDescricao(), oNegativo);
    }


    public Map<String, Integer> calcularDoadoresParaReceptor(Map<String, List<Pessoa>> pessoasPorTipoSanguineo) {
        Map<String, Integer> doadoresParaReceptor = new HashMap<>();

        for (String receptor : tabelaCompatibilidade.keySet()) {
            int quantidadeDoadores = 0;

            List<String> doadoresPermitidos = tabelaCompatibilidade.get(receptor).get(receberDe);
            for (String doadorPermitido : doadoresPermitidos) {
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

        for (Map.Entry<String, Map<String, List<String>>> entry : tabelaCompatibilidade.entrySet()) {
            String tipo = entry.getKey();
            Map<String, List<String>> dadosTipo = entry.getValue();

            stringBuilder.append("Tipo Sanguíneo: ").append(tipo).append("\n");

            List<String> podeDoarPara = dadosTipo.get(doarPara);
            List<String> podeReceberDe = dadosTipo.get(receberDe);

            stringBuilder.append("  Pode Doar Para: ").append(podeDoarPara).append("\n");
            stringBuilder.append("  Pode Receber De: ").append(podeReceberDe).append("\n\n");
        }

        return stringBuilder.toString();
    }
}
