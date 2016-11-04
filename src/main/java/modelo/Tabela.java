package modelo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class Tabela {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Map<String, String> campos = new HashMap<String, String>();

		CSVReader reader = new CSVReader(new FileReader("zap.csv"), ';', '"', 0);

		// Read CSV line by line and use the string array as you want
		String[] xpaths = reader.readNext();
		String[] colunas = reader.readNext();
		if (xpaths != null && colunas != null) {
			for (int i = 0; i < xpaths.length; i++) {
				campos.put(colunas[i], xpaths[i]);
			}
			// Verifying the read data here
			// System.out.println(Arrays.toString(nextLine));
		}
		// while ((nextLine = reader.readNext()) != null) {
		//
		// }
		reader.close();
		
		String url = "http://www.zapimoveis.com.br/venda/apartamentos/brasil/2-quartos/#{\"precominimo\":\"400000\",\"precomaximo\":\"700000\",\"filtrodormitorios\":\"2;3;\",\"filtrovagas\":\"1;2;\",\"areautilminima\":\"75\",\"areautilmaxima\":\"500\",\"parametrosautosuggest\":[{\"Bairro\":\"Praia de Belas\",\"Zona\":\"\",\"Cidade\":\"\",\"Agrupamento\":\"\",\"Estado\":\"\"},{\"Bairro\":\"Cidade Baixa\",\"Zona\":\"\",\"Cidade\":\"PORTO ALEGRE\",\"Agrupamento\":\"\",\"Estado\":\"RS\"},{\"Bairro\":\"Azenha\",\"Zona\":\"\",\"Cidade\":\"PORTO ALEGRE\",\"Agrupamento\":\"\",\"Estado\":\"RS\"},{\"Bairro\":\"Bom Fim\",\"Zona\":\"\",\"Cidade\":\"PORTO ALEGRE\",\"Agrupamento\":\"\",\"Estado\":\"RS\"},{\"Bairro\":\"Menino Deus\",\"Zona\":\"\",\"Cidade\":\"PORTO ALEGRE\",\"Agrupamento\":\"\",\"Estado\":\"RS\"},{\"Bairro\":\"Santana\",\"Zona\":\"\",\"Cidade\":\"PORTO ALEGRE\",\"Agrupamento\":\"\",\"Estado\":\"RS\"}],\"pagina\":\"1\",\"paginaOrigem\":\"ResultadoBusca\",\"semente\":\"2131068101\",\"tags\":[\"cobertura\",\"elevador\"],\"formato\":\"Lista\"}";
		
	}

	public static void write() throws Exception {
		String csv = "data.csv";
		CSVWriter writer = new CSVWriter(new FileWriter(csv));

		// Create record
		String[] record = "4,David,Miller,Australia,30".split(",");
		// Write the record to file
		writer.writeNext(record);

		// close the writer
		writer.close();
	}
}
