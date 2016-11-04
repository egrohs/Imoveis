package modelo;

import java.io.IOException;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Zap extends Site {
	public static void main(String[] args) throws Exception {
		new Zap();
	}

	public Zap() throws Exception {
		super(true);
	}

	@Override
	public List<Imovel> getData(List<Imovel> imoveis) throws IOException {
		// TODO esta pegando imoveis replicados a cada itera��o
		int paginas = Integer.parseInt(doc.select("div.pagination >span").get(0).text().replaceFirst("de ", ""));
		System.out.println(
				"$/m2	quartos	vagas	ano	andares	m2util	m2total	valor	condominio	id	rua	bairro	descr	carcIM	caracCond	url	temp");
		for (int pagina = 1; pagina <= paginas; pagina++) {
			Elements sels = doc.select("div.list-cell > a[href*=\"http\"]");
			for (Element a : sels) {
				Imovel im = new Imovel();
				im.setUrl(a.attr("href"));
				im.setValor(Float.parseFloat(
						a.select("span.price").text().replaceFirst("R\\$ ", "").replaceFirst("\\.000", "")));
				im.setBairro(a.select("span.bairro").text());
				im.setRua(a.select("span.loc2").get(0).text());
				for (Element li : a.select("li")) {
					String t = li.text();
					if (li.text().contains("quart")) {
						im.setQuartos(Integer.parseInt(t.split(" ")[0]));
					} else if (li.text().contains("vaga")) {
						im.setVagas(Integer.parseInt(t.split(" ")[0]));
					} else if (li.text().contains("m")) {
						try {
							im.setM2util(Integer.parseInt(t.split("m")[0]));
						} catch (NumberFormatException e) {

						}
						im.setTemp(t);
					}
				}
				imoveis.add(im);
				System.out.print(im.getValor() / im.getM2util() + "\t");
				System.out.println(im);
			}
			String url = getUrl().replaceFirst("\"?pagina\"?:\"?\\d+\"?", "\"pagina\":" + (pagina + 1));
			navega(url);
			// System.out.println(doc);
			System.out.println(url);
		}
		// for (Imovel imovel : imoveis) {
		// doc = navega(imovel.getUrl());
		// imovel.setDescricao(doc.select("div.descricaoOferta > p").text());
		// // "Ano de Construï¿½ï¿½o:""
		// // Andares:
		// Elements caracs= doc.select("div.caracteristicaOferta > p");
		// for (Element e : caracs) {
		// if (e.select("strong").text().contains("Ano de Constru")) {
		// imovel.setAno(Integer.parseInt(e.text().trim()));
		// } else if (e.select("strong").text().contains("Ano de Constru")) {
		// imovel.setAndares(Integer.parseInt(e.text().trim()));
		// }
		// }
		// System.out.println(imovel);
		// }
		return imoveis;
	}

	@Override
	public String getUrl() {
		return "http://www.zapimoveis.com.br/venda/apartamentos/brasil/2-quartos/#{\"precominimo\":\"400000\",\"precomaximo\":\"700000\",\"filtrodormitorios\":\"2;3;\",\"filtrovagas\":\"1;2;\",\"areautilminima\":\"75\",\"areautilmaxima\":\"500\",\"parametrosautosuggest\":[{\"Bairro\":\"Praia de Belas\",\"Zona\":\"\",\"Cidade\":\"\",\"Agrupamento\":\"\",\"Estado\":\"\"},{\"Bairro\":\"Cidade Baixa\",\"Zona\":\"\",\"Cidade\":\"PORTO ALEGRE\",\"Agrupamento\":\"\",\"Estado\":\"RS\"},{\"Bairro\":\"Azenha\",\"Zona\":\"\",\"Cidade\":\"PORTO ALEGRE\",\"Agrupamento\":\"\",\"Estado\":\"RS\"},{\"Bairro\":\"Bom Fim\",\"Zona\":\"\",\"Cidade\":\"PORTO ALEGRE\",\"Agrupamento\":\"\",\"Estado\":\"RS\"},{\"Bairro\":\"Menino Deus\",\"Zona\":\"\",\"Cidade\":\"PORTO ALEGRE\",\"Agrupamento\":\"\",\"Estado\":\"RS\"},{\"Bairro\":\"Santana\",\"Zona\":\"\",\"Cidade\":\"PORTO ALEGRE\",\"Agrupamento\":\"\",\"Estado\":\"RS\"}],\"pagina\":\"1\",\"paginaOrigem\":\"ResultadoBusca\",\"semente\":\"2131068101\",\"tags\":[\"cobertura\",\"elevador\"],\"formato\":\"Lista\"}";
	}
}
