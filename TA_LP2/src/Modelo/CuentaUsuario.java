package Modelo;
public class CuentaUsuario{
	private int idUsuario;
	private String nombreUsuario;
	private String contrasenha;
	private Permiso permise;
        private Boolean bloqueado;
        private String correo;
	public CuentaUsuario(){
            
        }
	public CuentaUsuario(int idUsuario,String nombreUsuario,String contrasenha,Permiso permise){
		this.idUsuario=idUsuario;
		this.nombreUsuario=nombreUsuario;
		this.contrasenha=contrasenha;
		this.permise=permise;
                this.correo ="";
	}
	
                /**
         * @return the bloqueado
         */
        public Boolean getBloqueado() {
            return bloqueado;
        }

       public String getCorreo(){
        return correo;
    }
    
    public void setCorreo(String correo){
        this.correo = correo;
    }
        /**
         * @param bloqueado the bloqueado to set
         */
        public void setBloqueado(Boolean bloqueado) {
            this.bloqueado = bloqueado;
        }
        
	public void setidUsuario(int idUsuario){
		this.idUsuario=idUsuario;
	}
	public int getidUsuario(){
		return this.idUsuario;
	}
	
	public void setnombreUsuario(String nombreUsuario){
		this.nombreUsuario=nombreUsuario;
	}
	public String getnombreUsuario(){
		return this.nombreUsuario;
	}
	
	public void setcontrasenha(String contrasenha){
		this.contrasenha=contrasenha;
	}
	public String getcontrasenha(){
		return this.contrasenha;
	}
	
	public void setpermise(Permiso permise){
		this.permise=permise;
	}
	public Permiso getpermise(){
		return this.permise;
	}
	public void verificacionDatos(String contras){
		// int count=0;
		// while(true){
			// if(this.contrasenha!=contras){
				// System.out.println("Es una contrasenha invalida");
				// count++;
				// if(count==3)
					// System.out.println("Intente otra vez en 5 minutos");
			// }else if(this.contrasenha==contras){
				// break;
			// }
		// }
		
	}
	
	public void registrarUsario(String nombreUsuario,String contrasenha){
	}
	
	
} 