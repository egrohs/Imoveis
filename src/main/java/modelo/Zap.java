package modelo;

import java.io.IOException;
import java.util.List;

import org.jsoup.nodes.Document;
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
	public List<Imovel> getData(Document doc, List<Imovel> imoveis) throws IOException {
		int paginas = Integer.parseInt(doc.select("div.pagination >span").get(0).text().replaceFirst("de ", ""));
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
						im.setQuartos(Integer.parseInt(t.split(" ")[0]));
					} else if (li.text().contains("m")) {
						// im.setM2util(Integer.parseInt(t.split("m")[0]));
						im.setTemp(t);
					}
				}
				imoveis.add(im);
			}
			navega(getUrl().replaceFirst("\"?pagina\"?:\\d+", "\"pagina\":" + pagina));
		}
		return imoveis;
	}

	@Override
	public String getUrl() {
		return "http://www.zapimoveis.com.br/venda/apartamentos/brasil/2-quartos/#{\"precominimo\":\"400000\",\"precomaximo\":\"700000\",\"filtrodormitorios\":\"2;3;\",\"filtrovagas\":\"1;2;\",\"areautilminima\":\"75\",\"areautilmaxima\":\"500\",\"parametrosautosuggest\":[{\"Bairro\":\"Praia de Belas\",\"Zona\":\"\",\"Cidade\":\"\",\"Agrupamento\":\"\",\"Estado\":\"\"}],\"pagina\":\"1\",\"paginaOrigem\":\"ResultadoBusca\",\"semente\":\"839310907\",\"formato\":\"Lista\"}";
	}
}