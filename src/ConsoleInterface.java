public class ConsoleInterface {

    private FactoryUnit fact;
    private boolean exitFlag;

    public ConsoleInterface(FactoryUnit fact) {
        this.fact = fact;
        this.exitFlag = true;
    }

    public boolean isExitFlag() {
        return exitFlag;
    }
    //��������� ����
    public void startMenu(){
        System.out.println("Hello!! \n" +
                "Print the number, what we must do: \n" +
                "1. Create new Unit \n" +
                "2. Print tree \n" +
                "0. Exit \n" +
                "*********************************");

        int startKey = ScannerGetter.getInstance().input.nextInt();
        switch (startKey){
            case 1 :
                this.unitCreator();
                break;
            case 2 :
                fact.printUnits();
                break;
            case 0 :
                this.exitFlag = false;
                break;
            default :
                System.out.println("Incorrect number \n" + "***********************");
                break;
        }
    }
    //*********************M��� �������� �����***************************
    public void unitCreator(){
        System.out.println("Unit Creator: \n" +
                "Print the number, what we must do: \n" +
                "1. Create class \n" +
                "2. Create individ \n" +
                "3. Create attribute \n" +
                "4. Create value \n" +
                "0. Back to Start Menu \n" +
                "**********************************");
        int creatorKey = ScannerGetter.getInstance().input.nextInt();
        switch (creatorKey) {
            case 1 :
                this.classCreator();
                break;
            case 2 :
                if(this.individCreator()){
                    break;
                }else this.unitCreator();

                break;
            case 3 :
                if(this.atrCreator()){
                    break;
                } else this.unitCreator();
                break;
            case 4 :
                if(this.valueCreator()){
                    break;
                } else this.unitCreator();

            case 0 :
                this.startMenu();
                break;
            default :
                System.out.println("incorrect number");
                break;
        }
    }
    //****************������� �������� ����-������********************
    private void classCreator(){
        System.out.print("Input class name: ");
        String c = ScannerGetter.getInstance().input.nextLine();
        String name = ScannerGetter.getInstance().input.nextLine();
        Unit u = Unit.createUnitClass(name);
        fact.addUnitClass(u);
    }
    //������� �������� ����-��������
    private boolean individCreator(){
        //***�������������� ��������
        if(this.hasNoClasses()){
            return false;
        }
        //***����� ���������� ������
        System.out.println("Choose parent class: ");
        int i = 1;
        for(Unit unitClass:fact.units) {
            System.out.println(Integer.toString(i) + ": " + unitClass);
            i++;
        }
        System.out.println("0: Back to Creator Menu \n" + "*********************");
        //***����� ������ �������� � ���� �����
        int parentKey = ScannerGetter.getInstance().input.nextInt();
        if (parentKey == 0){
            return false; // ������� � ����
        }
        System.out.print("Input individ name: ");
        String c = ScannerGetter.getInstance().input.nextLine();
        String name = ScannerGetter.getInstance().input.nextLine();
        Unit u = Unit.createUnitIndivid(name, fact.units.get(parentKey-1));
        //***
        return true;
    }
    //������� �������� ����-��������
    private boolean atrCreator(){
        //***�������������� ��������
        if(this.hasNoClasses()){
            return false;
        }
        if (this.hasNoIndivides()){
            return false;
        }
        //***����� ���������� � ��������
        System.out.println("Choose parent class and individe: ");
        int i = 1, j = 1;
        for(Unit unitClass:fact.units) {
            System.out.println(Integer.toString(i) + ": " + unitClass);
            i++;
            for (Unit unitIndivid : unitClass.getNextUnit()) {
                System.out.println("   " + Integer.toString(j) + ": " + unitIndivid);
                j++;
            }
            j = 1;
        }
        System.out.println("0: Back to Creator Menu");
        //***����� ���� � ���� �����
        System.out.print("Class: ");
        int parentKeyClass = ScannerGetter.getInstance().input.nextInt();
        if (parentKeyClass == 0){
            return false; // ������� � ����
        }
        System.out.print("Individ: ");
        int parentKeyIndivid = ScannerGetter.getInstance().input.nextInt();
        System.out.print("Input attribute name: ");
        String c = ScannerGetter.getInstance().input.nextLine(); 
        String name = ScannerGetter.getInstance().input.nextLine();
        Unit u = Unit.createUnitAtr(name, fact.units.get(parentKeyClass-1).getNextUnit().get(parentKeyIndivid-1));
        //***����������� ������������� ��������� ������� ���������
        System.out.println("Print 1 if you want to put a value in this attribute or 0 to put it later");
        String ch = ScannerGetter.getInstance().input.nextLine();
        if (ch.equals("1")) {
            System.out.print("Input value name: ");
            c = ScannerGetter.getInstance().input.nextLine();
            name = ScannerGetter.getInstance().input.nextLine();
            Unit un = Unit.createUnitValue(name, u);
        }
        //***
        return true;
    }
    //������� �������� �����-��������
    private boolean valueCreator(){
        //***�������������� ��������
        if(this.hasNoClasses()){
            return false;
        }
        if (this.hasNoIndivides()){
            return false;
        }
        if(this.hasNoAttributes()){
            return false;
        }
        //***����� �� ����� �������� � ��������
        System.out.println("Choose parent class, individe and attribute: ");
        int i = 1, j = 1, k = 1;
        for(Unit unitClass:fact.units) {
            System.out.println(Integer.toString(i) + ": " + unitClass);
            i++;
            for (Unit unitIndivid : unitClass.getNextUnit()) {
                System.out.println("   " + Integer.toString(j) + ": " + unitIndivid);
                j++;
                for(Unit unitAttribute : unitIndivid.getNextUnit()){
                    System.out.println("        " + Integer.toString(k) + ": " + unitAttribute);
                    k++;
                }
                k = 1;
            }
            j = 1;
        }
        System.out.println("0: Back to Creator Menu");
        //*** ���� ��������� ���� � ��������
        System.out.print("Class: ");
        int  parentKeyClass = ScannerGetter.getInstance().input.nextInt();
        if (parentKeyClass == 0){
            return false; // ������� � ����
        }
        System.out.print("Individ: ");
        int parentKeyIndivid = ScannerGetter.getInstance().input.nextInt();
        System.out.print("Attribute: ");
        int parentKeyAttribute = ScannerGetter.getInstance().input.nextInt();
        System.out.print("Input value name: ");
        String c = ScannerGetter.getInstance().input.nextLine(); 
        String name = ScannerGetter.getInstance().input.nextLine();
        Unit u = Unit.createUnitValue(name, fact.units.get(parentKeyClass-1).
                getNextUnit().get(parentKeyIndivid-1).
                getNextUnit().get(parentKeyAttribute-1));
        //***
        return true;
    }
    //������� �������� �� ��������� �����-�������
    private boolean hasNoClasses(){
        if(fact.units.isEmpty()){
            System.out.println("Has no classes");
            return true; //������: ������ ������ �������
        } else return false;
    }
    //������� �������� �� ��������� �����-���������
    private boolean hasNoIndivides(){
        for(Unit unitClass:fact.units) {
            if(!unitClass.getNextUnit().isEmpty()){
                return false;
            }
        }
        System.out.println("Has no individes");
        return true;
    }
    //������� �������� �� ���������� �����-���������
    private boolean hasNoAttributes(){
        for(Unit unitClass : fact.units){
            for(Unit unitIndivid : unitClass.getNextUnit()){
                if(!unitIndivid.getNextUnit().isEmpty()){
                    return false;
                }
            }
        }
        System.out.println("Has no attributes");
        return true;
    }
}