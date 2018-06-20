package Modelo;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class PedidoProducto{
	private int idPedido;
	private EstadoPedido estadoPed;
        
	private Date fechaRegPed;
	private Date fechaEntrPed;
	
	private Cliente cliente;
	private ArrayList<LineaPedidoProducto> listaLineasPedido;
	private DocumentoPago documPago;
        private float TotalPagar;
        private Boolean delivery;	
    
	public PedidoProducto(Cliente cliente,DocumentoPago documPago){
		this.cliente=cliente;
		this.documPago=documPago;
		listaLineasPedido= new ArrayList<LineaPedidoProducto>();
                this.fechaEntrPed = new Date();	
                this.TotalPagar = 0;
	}
        
        public PedidoProducto(){
            this.listaLineasPedido=new ArrayList<LineaPedidoProducto>();
            this.fechaEntrPed = new Date();
            this.fechaRegPed = new Date();  
            this.TotalPagar = 0;
            this.delivery=false;
        }
	
        /**
         * @return the delivery
         */
        public Boolean getDelivery() {
            return delivery;
        }

        /**
         * @param delivery the delivery to set
         */
        public void setDelivery(Boolean delivery) {
            this.delivery = delivery;
        }
        
	public void setidPedido(int idPedido){
		this.idPedido=idPedido;
	}
	public int getidPedido(){
		return this.idPedido;
	}
	
	public void setestadoPedo(EstadoPedido estadoPed){
		this.estadoPed=estadoPed;
	}
	public EstadoPedido getestadoPed(){
		return this.estadoPed;
	}
	
        
	public void setfechaRegPed(String fechaRegPed)throws Exception{
		SimpleDateFormat formt=new SimpleDateFormat("yyyy-MM-dd");
		this.fechaRegPed=formt.parse(fechaRegPed);
	}
	public Date getfechaRegPed(){
		return this.fechaRegPed;
	}
	
	public void setfechaEntrPed(String fechaEntrPed)throws Exception{
		SimpleDateFormat formt=new SimpleDateFormat("yyyy-MM-dd");
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
        
                /**
         * @return the TotalPagar
         */
        public float getTotalPagar() {
            float total=0;
            for(LineaPedidoProducto lpp : listaLineasPedido){
                if(lpp.getHabilitado())
                    total+= lpp.getCantidad()*lpp.getProducto().getprecio();
            }
            if(delivery)
                total=total+15;
            return total;
        }
        
        public int getNumLineasHabiles(){
            int i=0;
            for(LineaPedidoProducto lpp : listaLineasPedido){
                if(lpp.getHabilitado())
                    i++;
            }
            return i;
        }

        /**
         * @param TotalPagar the TotalPagar to set
         */
        public void setTotalPagar(float TotalPagar) {
            this.TotalPagar = TotalPagar;
        }
        
	public void setdocumPago(DocumentoPago documPago){
		this.documPago=documPago;
	}
	public DocumentoPago getdocumPago(){
		return this.documPago;		
	}  
	
	public void registrarDocPago(){
	}
	
	public void imprimirDocPago(){	
	}
        
            /**
        * @return the listaLineasPedido
        */
       public ArrayList<LineaPedidoProducto> getListaLineasPedido() {
           return listaLineasPedido;
       }

       /**
        * @param listaLineasPedido the listaLineasPedido to set
        */
       public void setListaLineasPedido(ArrayList<LineaPedidoProducto> listaLineasPedido) {
           this.listaLineasPedido = listaLineasPedido;
       }
        
        public void agregarLinea(LineaPedidoProducto nIngr){
            getListaLineasPedido().add(nIngr);
	}
}