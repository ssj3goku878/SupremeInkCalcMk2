/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supremeinkcalcmk2;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Archa
 */
public class FormulaList {

    public SimpleStringProperty BaseFormula = new SimpleStringProperty();
    public SimpleStringProperty BasePT = new SimpleStringProperty();

    
    public String getBaseFormula() {
        return BaseFormula.get();
    }

    public void setBaseFormula(String BaseFormulaStr) {
        BaseFormula.set(BaseFormulaStr);
    }

    public String getBasePT() {
        return BasePT.get();
    }

    public void setBasePT(String BasePTStr) {
        BasePT.set(BasePTStr);
    }

}
