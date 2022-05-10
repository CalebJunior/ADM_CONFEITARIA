package com.example.app_firebase.Model;

public class Pedidos {
    private String ID_Cliente;
    private String ID_Funcionario;
    private long Status;
    private String Dt_entrega;
    private String Dt_pedido;

    public Pedidos() {
    }

    public String getID_Cliente() {
        return ID_Cliente;
    }

    public void setID_Cliente(String ID_Cliente) {
        this.ID_Cliente = ID_Cliente;
    }

    public String getID_Funcionario() {
        return ID_Funcionario;
    }

    public void setID_Funcionario(String ID_Funcionario) {
        this.ID_Funcionario = ID_Funcionario;
    }

    public long getStatus() {
        return Status;
    }

    public void setStatus(long status) {
        Status = status;
    }

    public String getDt_entrega() {
        return Dt_entrega;
    }

    public void setDt_entrega(String dt_entrega) {
        Dt_entrega = dt_entrega;
    }

    public String getDt_pedido() {
        return Dt_pedido;
    }

    public void setDt_pedido(String dt_pedido) {
        Dt_pedido = dt_pedido;
    }
}
