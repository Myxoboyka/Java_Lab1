import java.util.ArrayList;

public class Unit {
    //строковые константы
    private static final String TYPE_UNIT_CLASS = "class";
    private static final String TYPE_UNIT_INDIVID = "individ";
    private static final String TYPE_UNIT_ATR = "attribute";
    private static final String TYPE_UNIT_VALUE = "value";

    private static final String ATR_NAME_ID = "id";

    //атрибуты класса Узел
    private String name; //имя
    private String type; //тип
    private ArrayList<Unit> nextUnit;
    private Unit previousUnit;

    //закрытый конструктор, так как создание реализовано отдельными методами
    private Unit(String name, String type) {
        this.name = name;
        this.type = type;
        this.nextUnit = new ArrayList<Unit>();
    }

    //геттеры
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public ArrayList<Unit> getNextUnit() {
        return nextUnit;
    }
    public Unit getPreviousUnit() {
        return previousUnit;
    }
    //вывод в зависимости от типа узла
    public String toString() {
        String s = "";
        switch (this.type){
            case TYPE_UNIT_CLASS:
                s = this.type+ ": " + this.name;
                return s;
            case TYPE_UNIT_INDIVID:
                s = this.type+ ": " + this.name;
                return s;
            case TYPE_UNIT_ATR:
                s = this.type+ ": " + this.name;
                return s;
            case TYPE_UNIT_VALUE:
                s = this.type+ ": " + this.name;
                return s;
            default:
                return "";
        }
    }

    //сеттеры для создания соединения
    public void setNext(Unit unit){
        this.nextUnit.add(unit);
    }
    public void setPrevious(Unit unit){
        this.previousUnit = unit;
    }

    //создатели
    //создать узел-класс
    public static Unit createUnitClass(String name) {
        Unit unit = new Unit(name, TYPE_UNIT_CLASS);
        return unit;
    }
    //создать узел-индивид
    public static Unit createUnitIndivid(String name, Unit previous){
        Unit unit = new Unit(name, TYPE_UNIT_INDIVID);
        unit.setPrevious(previous);
        previous.setNext(unit);
        //*** автоматическое добавление id для индивидов
        IdGetter idgetter = IdGetter.getInstance();
        Unit id = createUnitAtr(ATR_NAME_ID,unit);
        Unit idValue = createUnitValue(idgetter.getId(), id);
        //***
        return unit;
    }
    //создать узел-атрибут
    public static Unit createUnitAtr(String name, Unit previous){
        if (previous.type == TYPE_UNIT_INDIVID){
            Unit unit = new Unit(name,TYPE_UNIT_ATR);
            unit.setPrevious(previous);
            previous.setNext(unit);
            return unit;
        } else{
            System.out.println("Error");
            return null;
        }
    }
    //создать узел-значение
    public static Unit createUnitValue(String name, Unit previous){
        if (previous.type == TYPE_UNIT_ATR && previous.getNextUnit().isEmpty()){
            Unit unit = new Unit(name,TYPE_UNIT_VALUE);
            unit.setPrevious(previous);
            previous.setNext(unit);
            return unit;
        } else{
            System.out.println("too many values");
            return null;
        }
    }
}