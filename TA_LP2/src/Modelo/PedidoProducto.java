package Modelo;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class PedidoProducto{
	private int idPedido;
	private estadoPedido estadoPed;
	
	private Date fechaRegPed;
	private Date fechaEntrPed;
	
	private Cliente cliente;
	private ArrayList<LineaPedidoProducto> listaLineasPedido;
	private DocumentoPago documPago;
	
	public PedidoProducto(Cliente cliente,DocumentoPago documPago){
		this.cliente=cliente;
		this.documPago=documPago;
		listaLineasPedido= new ArrayList<LineaPedidoProducto>();
                this.fechaEntrPed = new Date();
                this.fechaRegPed = new Date();
		// this.idPedido=idPedido;
		// this.estadoPed=estadoPed;
		// //this.cuentaUs=cuentaUs;
		
		// String output;
		// try{
			// this.fechaRegPed=new Date();
			// SimpleDateFormat formt1=new SimpleDateFormat("dd/MM/yyyy");
			// this.fechaRegPed=formt1.parse(fechaRegPed);
		// }catch(ParseException e){
			// output="error";
		// }
		
		// try{
			// this.fechaEntrPed=new Date();		
			// SimpleDateFormat formt2=new SimpleDateFormat("dd/MM/yyyy");
			// this.fechaEntrPed=formt2.parse(fechaEntrPed);
		// }catch(ParseException e){
			// output="error";
		// }	
	}
        
        public PedidoProducto(){
            this.listaLineasPedido=new ArrayList<LineaPedidoProducto>();
            this.fechaEntrPed = new Date();
            this.fechaRegPed = new Date();
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
	
	public void setcliente(Cliente cliente){
		this.cliente=cliente;
	}
	public Cliente getcliente(){
		return this.cliente;		
	}	
	
	public void setdocumPago(DocumentoPago documPago){
		this.documPago=documPago;
	}
	public DocumentoPago getdocumPago(){
		return this.documPago;		
	}
	  
	public int subtotal(){
		//solo para compilar
		return 1;
	}
	
	public void registrarDocPago(){
	}
	
	public void imprimirDocPago(){	
	}
        
        public void agregarLinea(LineaPedidoProducto nIngr){
            listaLineasPedido.add(nIngr);
	}
}