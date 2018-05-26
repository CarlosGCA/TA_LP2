package Modelo;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class PedidoInterno{
        private estadoPedido estadoPed;
        private int idPedido;

	private Date fechaRegPed;
	private Date fechaEntrPed;
    
	private ArrayList<LineaPedidoInterno> listaLineasPedido;
	private Almacen almacenOrigen;
	private Almacen almacenFin;
	
	public PedidoInterno(Almacen almacenOrigen,Almacen almacenFin){
		this.almacenOrigen=almacenOrigen;
		this.almacenFin=almacenFin;	
                this.fechaEntrPed = new Date();
                this.fechaRegPed = new Date();
		listaLineasPedido=new ArrayList<LineaPedidoInterno>();
	}
	public PedidoInterno(){
                this.fechaEntrPed = new Date();
                this.fechaRegPed = new Date();
		listaLineasPedido=new ArrayList<LineaPedidoInterno>();
	}
	
	public void setidPedido(int idPedido){
		this.idPedido=idPedido;
	}
	public int getidPedido(){
		return this.idPedido;
	}
	
	public void setestadoPedo(estadoPedido estadoPed){
		this.idPedido=idPedido;
	}
	public estadoPedido getestadoPed(){
		return this.estadoPed;
	}
	public void setfechaRegPed(String fechaRegPed)throws Exception{
		SimpleDateFormat formt=new SimpleDateFormat("dd/MM/yyyy");
		this.fechaRegPed=formt.parse(fechaRegPed);
	}
	public Date getfechaRegPed(){
		return this.fechaRegPed;
	}
	
	public void setfechaEntrPed(String fechaEntrPed)throws Exception{
		SimpleDateFormat formt=new SimpleDateFormat("dd/MM/yyyy");
		this.fechaEntrPed=formt.parse(fechaEntrPed);
	}
	public Date getfechaEntrPed(){
		return this.fechaEntrPed;
	}
	public void setlistaInsumoPedido(ArrayList<LineaPedidoInterno> listaInsumoPedido){
		this.listaLineasPedido=listaInsumoPedido;
	}
	public ArrayList<LineaPedidoInterno> getlistaInsumoPedido(){
		return this.listaLineasPedido;
	}
	
	public void setalmacenOrigen(Almacen almacenOrigen){
		this.almacenOrigen=almacenOrigen;
	}
	public Almacen getalmacenOrigen(){
		return this.almacenOrigen;
	}
	
	public void setalmacenFin(Almacen almacenFin){
		this.almacenFin=almacenFin;
	}
	public Almacen getalmacenFin(){
		return this.almacenFin;
	}
	
	public void agregarLinea(LineaPedidoInterno nIngr){
            listaLineasPedido.add(nIngr);
	}
}