//����� ��� �������������� �������������� ������������������ id
//���������� �� ������� ��������
public class IdGetter {
    private static final int NUM_OF_ID = 100;
    private static IdGetter instance;
    private static int counter;
    private static String[] id;

    //���������� ���������
    public static IdGetter getInstance() {
        if (instance == null){
            instance = new IdGetter();
            id = new String[NUM_OF_ID];
            for (int i = 0; i < NUM_OF_ID; i++){
                id[i] = Integer.toString(i);
            }
            counter = 0;
        }
        return instance;
    }

    private IdGetter() {
    }
    //����� ��� ��������� id
    public String getId(){
        if(counter == NUM_OF_ID) {
            return "��������� ������";
        } else return id[counter++];
    }
}