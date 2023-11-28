package com.example.wktechnology;

import com.example.wktechnology.model.entity.Pessoa;
import com.example.wktechnology.model.repository.PessoaRepository;
import com.example.wktechnology.utils.DateFormatter;
import com.example.wktechnology.utils.enums.EstadoBrasileiro;
import com.example.wktechnology.utils.enums.Sexo;
import com.example.wktechnology.utils.enums.TipoSanguineo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.javac.Main;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = "com.example.wktechnology")
@EnableJpaRepositories(basePackages = "com.example.wktechnology.*")
@EntityScan("com.example.wktechnology.*")
public class WktechnologyApplication {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private ApplicationContext ctx;

	public static void main(String[] args) {
		SpringApplication.run(WktechnologyApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(){
		return args -> {

			String filePath = "pessoas.json";

			ObjectMapper objectMapper = new ObjectMapper();

			InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(filePath);

			List<Object> pessoasObject = objectMapper.readValue(inputStream, new TypeReference<List<Object>>() {});

			for (Object p : pessoasObject) {
				pessoaRepository.save(fromMap(p));
			}

			System.out.println("####### WktechnologyApplication - Started #######");
		};
	}

	public static Pessoa fromMap(Object map) {
		Pessoa pessoa = new Pessoa();

		pessoa.setNome((String) ((Map<?, ?>) map).get("nome"));
		pessoa.setCpf((String) ((Map<?, ?>) map).get("cpf"));
		pessoa.setRg((String) ((Map<?, ?>) map).get("rg"));

		pessoa.setDataNasc(DateFormatter.parseDataNascimento((String) ((Map<?, ?>) map).get("data_nasc")));

		pessoa.setSexo(  Sexo.fromDescricao((String)  ((Map<?, ?>) map).get("sexo") ));

		pessoa.setMae((String) ((Map<?, ?>) map).get("mae"));
		pessoa.setPai((String) ((Map<?, ?>) map).get("pai"));
		pessoa.setEmail((String) ((Map<?, ?>) map).get("email"));
		pessoa.setCep((String) ((Map<?, ?>) map).get("cep"));
		pessoa.setEndereco((String) ((Map<?, ?>) map).get("endereco"));
		pessoa.setNumero(Integer.parseInt(String.valueOf(((Map<?, ?>) map).get("numero"))));
		pessoa.setBairro((String) ((Map<?, ?>) map).get("bairro"));
		pessoa.setCidade((String) ((Map<?, ?>) map).get("cidade"));

		pessoa.setEstado( EstadoBrasileiro.fromSigla( (String) ((Map<?, ?>) map).get("estado")) );

		pessoa.setTelefoneFixo((String) ((Map<?, ?>) map).get("telefone_fixo"));
		pessoa.setCelular((String) ((Map<?, ?>) map).get("celular"));
		pessoa.setAltura(Double.parseDouble(String.valueOf(((Map<?, ?>) map).get("altura"))));
		pessoa.setPeso(Integer.parseInt(String.valueOf(((Map<?, ?>) map).get("peso"))));


		pessoa.setTipoSanguineo( TipoSanguineo.fromDescricao( (String) ((Map<?, ?>) map).get("tipo_sanguineo") ) );

		return pessoa;
	}
}
