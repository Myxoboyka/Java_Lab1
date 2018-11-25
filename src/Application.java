public class Application {
    public static void main(String args[]){
        FactoryUnit f = new FactoryUnit();
        ConsoleInterface con = new ConsoleInterface(f);
        while (con.isExitFlag()){
            con.startMenu();
        }
    }
}