package Modelo;
import java.util.ArrayList;
public class Almacen{
	private int idAlmacen;
	private String nombreAlm;
	private ArrayList<Ingrediente>listIngrediente;
	
	public Almacen(){
		listIngrediente=new ArrayList<Ingrediente>();
	}
	public Almacen(int idAlmacen,String nombreAlm){
		this.idAlmacen=idAlmacen;
		this.nombreAlm=nombreAlm;
		this.listIngrediente=new ArrayList<Ingrediente>();
	}
	public void setidAlmacen(int idAlmacen){
		this.idAlmacen=idAlmacen;
	}
	public int getidAlmacen(){
		return this.idAlmacen;
	}
	
	public void setnombreAlm(String nombreAlm){
		this.nombreAlm=nombreAlm;
	}
	public String getnombreAlm(){
		return this.nombreAlm;
	}
	
	public void setlistIngrediente(ArrayList<Ingrediente> listIngrediente){
		this.listIngrediente=listIngrediente;
	}
	public ArrayList<Ingrediente> getlistIngrediente(){
		return this.listIngrediente;
	}
	
	public void reporteInventario(){
	}
	
	public void movimientoDeInsumo(int codInsumo){
	}
	
}