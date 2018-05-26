package Modelo;
public class Natural extends Cliente{
	private String DNI;
	private String nombre;
	private String apellidos;

	public Natural(int id_cliente, int telefono, String direccion, String correo,String cuenta,
					String DNI, String nombre, String apellidos){
		super(id_cliente, telefono, correo, direccion, cuenta);
		setDNI(DNI);
		setNombre(nombre);
		setApellidos(apellidos);
	}

    public Natural(){
    }

	public String getDNI() {
        return DNI;
    }

    /**
     * @param DNI the DNI to set
     */
    public void setDNI(String DNI) {
        this.DNI = DNI;
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

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}