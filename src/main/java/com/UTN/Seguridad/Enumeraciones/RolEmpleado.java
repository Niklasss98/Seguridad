package com.UTN.Seguridad.Enumeraciones;

public enum RolEmpleado {
    Cajero("Cajero"),
    Delivery("Delivery"),
    Cocinero("Cocinero"),
    Administrador("Administrador");

    private String texto;

    private RolEmpleado(String texto){
        this.texto = texto;
    }

    public String getTexto(){
        return texto;
    }

}
