package Modelo;
import java.util.ArrayList;
public class Producto{
	private int idProducto;
	private String nombProducto;
	private ArrayList<Ingrediente> Receta;
	private float precio;
	
	public Producto(){
		Receta=new ArrayList<Ingrediente>();
	}
	public Producto(int idProducto,String nombProducto,float precio){
		this.idProducto=idProducto;
		this.nombProducto=nombProducto;
		this.precio=precio;
		Receta=new ArrayList<Ingrediente>();
	}
	
	public void setidProducto(int idProducto){
		this.idProducto=idProducto;
	}
	public int getidProducto(){
		return this.idProducto;
	}
	
	public void setnombProducto(String nombProducto){
		this.nombProducto=nombProducto;
	}
	public String getnombProducto(){
		return this.nombProducto;
	}
	
	public void setReceta(ArrayList<Ingrediente> Receta){
		this.Receta=Receta;
	}
	public ArrayList<Ingrediente> getReceta(){
		return this.Receta;
	}	
	
	public void setprecio(float precio){
		this.precio=precio;
	}
	public float getprecio(){
		return this.precio;
	}
        public void agregarIngrediente(Ingrediente i){
            Receta.add(i);
        }
	
}