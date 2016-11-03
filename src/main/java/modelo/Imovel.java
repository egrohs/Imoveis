package modelo;

import java.lang.reflect.Field;

public class Imovel {
	private int quartos, vagas, ano, andares;
	private float m2util, m2total, valor, condominio;
	private String id, rua, bairro, descricao, caracsIm, caracCom, url, temp;

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Field f : Imovel.class.getDeclaredFields()) {
			try {
				sb.append(f.get(this) + "\t");
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public int getQuartos() {
		return quartos;
	}

	public void setQuartos(int quartos) {
		this.quartos = quartos;
	}

	public int getVagas() {
		return vagas;
	}

	public void setVagas(int vagas) {
		this.vagas = vagas;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public float getM2util() {
		return m2util;
	}

	public void setM2util(float m2util) {
		this.m2util = m2util;
	}

	public float getM2total() {
		return m2total;
	}

	public void setM2total(float m2total) {
		this.m2total = m2total;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public float getCondominio() {
		return condominio;
	}

	public void setCondominio(float condominio) {
		this.condominio = condominio;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCaracsIm() {
		return caracsIm;
	}

	public void setCaracsIm(String caracsIm) {
		this.caracsIm = caracsIm;
	}

	public String getCaracCom() {
		return caracCom;
	}

	public void setCaracCom(String caracCom) {
		this.caracCom = caracCom;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public int getAndares() {
		return andares;
	}

	public void setAndares(int andares) {
		this.andares = andares;
	}
}