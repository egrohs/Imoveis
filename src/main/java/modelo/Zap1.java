package modelo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Zap1 extends Scrap {
	public static void main(String[] args) throws Exception {
		new Zap1();
	}

	public Zap1() throws Exception {
		super(true);
	}

	@Override
	public String getUrl() {
		return "http://www.zapimoveis.com.br/venda/apartamentos/brasil/2-quartos/#{\"precominimo\":\"400000\",\"precomaximo\":\"700000\",\"filtrodormitorios\":\"2;3;\",\"filtrovagas\":\"1;2;\",\"areautilminima\":\"75\",\"areautilmaxima\":\"500\",\"parametrosautosuggest\":[{\"Bairro\":\"Praia de Belas\",\"Zona\":\"\",\"Cidade\":\"\",\"Agrupamento\":\"\",\"Estado\":\"\"},{\"Bairro\":\"Cidade Baixa\",\"Zona\":\"\",\"Cidade\":\"PORTO ALEGRE\",\"Agrupamento\":\"\",\"Estado\":\"RS\"},{\"Bairro\":\"Azenha\",\"Zona\":\"\",\"Cidade\":\"PORTO ALEGRE\",\"Agrupamento\":\"\",\"Estado\":\"RS\"},{\"Bairro\":\"Bom Fim\",\"Zona\":\"\",\"Cidade\":\"PORTO ALEGRE\",\"Agrupamento\":\"\",\"Estado\":\"RS\"},{\"Bairro\":\"Menino Deus\",\"Zona\":\"\",\"Cidade\":\"PORTO ALEGRE\",\"Agrupamento\":\"\",\"Estado\":\"RS\"},{\"Bairro\":\"Santana\",\"Zona\":\"\",\"Cidade\":\"PORTO ALEGRE\",\"Agrupamento\":\"\",\"Estado\":\"RS\"}],\"pagina\":\"1\",\"paginaOrigem\":\"ResultadoBusca\",\"semente\":\"2131068101\",\"tags\":[\"cobertura\",\"elevador\"],\"formato\":\"Lista\"}";
	}
}
