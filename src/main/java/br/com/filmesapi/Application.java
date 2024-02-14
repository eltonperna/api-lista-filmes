package br.com.filmesapi;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import br.com.filmesapi.entity.Filme;
import br.com.filmesapi.repository.FilmeRepository;
import br.com.filmesapi.service.ProdutorService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = { "br.com.filmesapi" })
public class Application {
	
	@Autowired
	private FilmeRepository filmeRepository;
	
	@Autowired
	private ProdutorService produtorService;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext appContext) {
		return args -> {

			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			InputStream in = cl.getResourceAsStream("movielist.csv");
			Reader reader = new InputStreamReader(in);
			Iterable<CSVRecord> records = CSVFormat.RFC4180
					.withDelimiter(';')
					.withHeader("ano","titulo","studio","produtor","vencedor")
					.parse(reader);

			for (CSVRecord record : records) {
				if (record.getRecordNumber() == 1) {
					continue;
				}

				Filme filme = filmeRepository.save(new Filme(Integer.valueOf(record.get("ano")), record.get("titulo"), record.get("vencedor")));

				produtorService.saveProdutores(filme, record.get("produtor"));


			}

        };
	}
	
}
