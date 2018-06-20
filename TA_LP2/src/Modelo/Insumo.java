package Modelo;

public class Insumo{

    

    
	private int idInsumo;
	private String descripcion;
        private String nombre;
	private unidadMed unidMed;
        private int cantidaMinima;
        
        public Insumo(){};
	
	public Insumo(int idInsumo,String descripcion,unidadMed unidMed){
		this.idInsumo=idInsumo;
		this.descripcion=descripcion;
		this.unidMed=unidMed;
	}
        
            /**
         * @return the cantidaMinima
         */
        public int getCantidaMinima() {
            return cantidaMinima;
        }

        /**
         * @param cantidaMinima the cantidaMinima to set
         */
        public void setCantidaMinima(int cantidaMinima) {
            this.cantidaMinima = cantidaMinima;
        }
	
	public void setidInsumo(int idInsumo){
		this.idInsumo=idInsumo;
	}
	public int getidInsumo(){
		return this.idInsumo;
	}
	
	public void setdescripcionm(String descripcion){
		this.descripcion=descripcion;
	}
	public String getdescripcion(){
		return this.descripcion;
	}
	
	public void setunidMed(unidadMed unidMed){
		this.unidMed=unidMed;
	}
	public unidadMed getunidMed(){
		return this.unidMed;
	}
        
            /**
         * @return the nombre
         */
        public String getNombre() {
            return nombre;
        }

        /**
         * @param nombre the nombre to set
         */
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
	
}