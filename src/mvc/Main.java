package src.mvc;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Model model = new Model();
        Controller controller = new Controller(model);
        Viewer view = new Viewer(controller, sc);

        view.run();
    }
}
