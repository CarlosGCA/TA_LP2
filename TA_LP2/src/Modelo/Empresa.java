package Modelo;
public class Empresa extends Cliente{
	private String ruc;
	private String razonSocial;

	public Empresa(int id_cliente, int telefono, String correo ,String direccion, String cuenta,
					String ruc, String nombreEmpresa){
		super(id_cliente, telefono, correo, direccion, cuenta);
		setRuc(ruc);
		setRazonSocial(nombreEmpresa);
	}

    public Empresa(){
    }

	public String getRuc() {
        return ruc;
    }

    /**
     * @param ruc the ruc to set
     */
    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    /**
     * @return the nombreEmpresa
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * @param nombreEmpresa the nombreEmpresa to set
     */
    public void setRazonSocial(String nombreEmpresa) {
        this.razonSocial = nombreEmpresa;
    }
}