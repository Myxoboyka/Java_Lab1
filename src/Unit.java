import java.util.ArrayList;

public class Unit {
    //��������� ���������
    private static final String TYPE_UNIT_CLASS = "class";
    private static final String TYPE_UNIT_INDIVID = "individ";
    private static final String TYPE_UNIT_ATR = "attribute";
    private static final String TYPE_UNIT_VALUE = "value";

    private static final String ATR_NAME_ID = "id";

    //�������� ������ ����
    private String name; //���
    private String type; //���
    private ArrayList<Unit> nextUnit;
    private Unit previousUnit;

    //�������� �����������, ��� ��� �������� ����������� ���������� ��������
    private Unit(String name, String type) {
        this.name = name;
        this.type = type;
        this.nextUnit = new ArrayList<Unit>();
    }

    //�������
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
    //����� � ����������� �� ���� ����
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

    //������� ��� �������� ����������
    public void setNext(Unit unit){
        this.nextUnit.add(unit);
    }
    public void setPrevious(Unit unit){
        this.previousUnit = unit;
    }

    //���������
    //������� ����-�����
    public static Unit createUnitClass(String name) {
        Unit unit = new Unit(name, TYPE_UNIT_CLASS);
        return unit;
    }
    //������� ����-�������
    public static Unit createUnitIndivid(String name, Unit previous){
        Unit unit = new Unit(name, TYPE_UNIT_INDIVID);
        unit.setPrevious(previous);
        previous.setNext(unit);
        //*** �������������� ���������� id ��� ���������
        IdGetter idgetter = IdGetter.getInstance();
        Unit id = createUnitAtr(ATR_NAME_ID,unit);
        Unit idValue = createUnitValue(idgetter.getId(), id);
        //***
        return unit;
    }
    //������� ����-�������
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
    //������� ����-��������
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