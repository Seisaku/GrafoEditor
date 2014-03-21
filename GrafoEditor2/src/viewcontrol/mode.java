/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewcontrol;

/**
 *
 */
public enum mode {

    sel, addNode, addEdge, remNode, remEdge;

    static {
        sel.msg = "Modo Selecionar";
        addNode.msg = "Adicionar Vértice";
        addEdge.msg = "Adicionar Aresta";
        remNode.msg = "Remover Vértice";
        remEdge.msg = "Remover Aresta";

    }

    private String msg;

    public String getMsg() {
        return msg;
    }

}
