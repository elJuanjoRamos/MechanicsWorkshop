
package controllers;

public class InterpreterController {

    private static InterpreterController instance;
    
    public static InterpreterController getInstance() {
        if(instance == null) {
            instance = new InterpreterController();
        }
        return instance;
    }

    public InterpreterController() {
    }
    
    public void interpret(String instruction) {
        
    }
    
}
