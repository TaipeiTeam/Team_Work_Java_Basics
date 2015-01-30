package sample;

/**
 * Created by radko on 30.1.2015 г..
 */
public interface ControlledScreen {

    //this method will allow the injection of the Parent ScreenPane
    public void setScreenParent(ScreensController screenPage);
}
