package edu.project4;

public class Main {
    private Main() {
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.runMultiThreadRender();
    }
}
