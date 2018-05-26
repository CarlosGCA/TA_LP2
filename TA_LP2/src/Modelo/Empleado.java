package Modelo;

import java.util.Date;

public class Empleado {

    private int ID;
    private int DNI;
    private String nombre;
    private String apellido;//quiero agregar eso
    private char sexo;
    private String fechaNac;
    private Turno turno;
    private CuentaUsuario usuario;
    private Puesto puesto;

    public Empleado(int ID,int DNI, String nombre, String apellido, char sexo, String fechaNac, Turno turno, CuentaUsuario usuario) {
        setID(ID);
        setDNI(DNI);
        setNombre(nombre);
        setApellido(apellido);
        setSexo(sexo);
        setFechaNac(fechaNac);
        setUsuario(usuario);
        setTurno(turno);
    }

    public Empleado() {
    }

 
    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }
    public void setUsuario(CuentaUsuario usuario) {
        this.usuario = usuario;
    }

    public CuentaUsuario getUsuario() {
        return usuario;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Turno getTurno() {
        return turno;
    }
    

    public Puesto getPuesto() {
        return puesto;
    }


    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }
//    @Override
//    public String toString() {
//        return turno.toString();
//    }
//    

}
