package appjava00;

public class Test03 {

    public static void main(String[] args) {
        // variables
        String t1 = "Java";
        String t2 = "java";
        //comparar
        if (t1.equals(t2)) {
            System.out.println("Verdadero");
        } else {
            System.out.println("Falso");
        }
        System.out.println("Fin de programa");
        //otra forma
        if (t1.compareTo(t2)==0) {
            System.out.println("Verdadero");
        }else{
            System.out.println("Falso");
        }
    }

}
