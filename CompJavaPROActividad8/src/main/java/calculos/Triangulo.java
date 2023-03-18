/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculos;

/**
 *
 * @author patoe
 */
public class Triangulo 
{
    //Atributos
    private int base;
    private int altura;
    private int perimetro;
    private int area;

    //Constructor: aquí ya se realiza directamente el cálculo del area y del perimetro
    public Triangulo(String base, String altura) {
        this.base = Integer.parseInt(base);
        this.altura = Integer.parseInt(altura);
        this.perimetro = this.base*3;
        this.area = this.base*this.altura/2; 
    }

    //Getters y Setters de cada atributo
    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPerimetro() {
        return perimetro;
    }

    public void setPerimetro(int perimetro) {
        this.perimetro = perimetro;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }
}
