/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewcontrol;

/**
 *
 * @author I839169
 */
public abstract class ItemControl {

    /**
     *
     */
    private boolean selected;
    
    public abstract String getName();

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    public abstract void remove();
    
    
}
