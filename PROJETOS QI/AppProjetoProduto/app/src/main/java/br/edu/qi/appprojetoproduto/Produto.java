package br.edu.qi.appprojetoproduto;

public class Produto {
    private int id;
    private String nome;
    private String categoria;
    private float valor;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "\n" +
                "ID: "+this.id+"\n"+
                "Nome: "+this.nome+"\n"+
                "Categoria: "+this.categoria+"\n"+
                "Valor: "+this.valor;
    }
}
