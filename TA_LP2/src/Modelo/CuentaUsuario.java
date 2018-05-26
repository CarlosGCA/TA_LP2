package Modelo;
public class CuentaUsuario{
	private int idUsuario;
	private String nombreUsuario;
	private String contrasenha;
	private Permiso permise;
	public CuentaUsuario(){
            
        }
	public CuentaUsuario(int idUsuario,String nombreUsuario,String contrasenha,Permiso permise){
		this.idUsuario=idUsuario;
		this.nombreUsuario=nombreUsuario;
		this.contrasenha=contrasenha;
		this.permise=permise;
	}
	
	public void setidProducto(int idUsuario){
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