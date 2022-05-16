package com.example.app_firebase.Model;

public class Pedidos {
    private String ID_Cliente;
    private String ID_Produto;
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

    public String getID_Produto() {
        return ID_Produto;
    }

    public void setID_Produto(String ID_Produto) {
        this.ID_Produto = ID_Produto;
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
