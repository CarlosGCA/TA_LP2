package Modelo;
public class Ingrediente{
        private int idIngrediente;
	private int cantidad;
	private Insumo insumo;
	
        public Ingrediente(){
            
        }
        
	public Ingrediente(int cantidad,Insumo insumo){
		this.cantidad=cantidad;
		this.insumo=insumo;
	}
        
        public void setidIngrediente(int id){
            this.idIngrediente=id;
        }
        
        public int getidIngrediente(){
            return this.idIngrediente;
        }
	
	public void setcantidad(int cantidad){
		this.cantidad=cantidad;
	}
	public int getcantidad(){
		return this.cantidad;
	}
		
	public void setinsumo(Insumo insumo){
		this.insumo=insumo;
	}
	public Insumo getinsumo(){
		return this.insumo;
	}
	
}