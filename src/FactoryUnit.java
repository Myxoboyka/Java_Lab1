import java.util.ArrayList;

public class FactoryUnit {
    public ArrayList<Unit> units;

    public void addUnitClass(Unit unit){
        units.add(unit);
    }

    public FactoryUnit() {
        this.units = new ArrayList<Unit>();

    }

    //функция вывода всего дерева
    public void printUnits(){
        for(Unit unitClass:this.units){
            System.out.println(unitClass);
            for(Unit unitIndivid:unitClass.getNextUnit()){
                System.out.println("   " + unitIndivid);
                for(Unit unitAtr:unitIndivid.getNextUnit()){
                    System.out.println("        " + unitAtr);
                    for(Unit unitValue:unitAtr.getNextUnit()){
                        System.out.println("            " + unitValue);
                    }
                }
            }
        }
    }
}