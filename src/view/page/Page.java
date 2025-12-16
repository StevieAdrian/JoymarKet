package view.page;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public abstract class Page extends BorderPane {

	public Page() {
		
    }
    
    protected void init() {
        start();
        setLayout();
        setStyle();
        setEvent();
    }
    
    public void initialize() {	
        init();
    }
	 
    protected abstract void start();
	protected abstract void setLayout();
	protected abstract void setStyle();
	protected abstract void setEvent();
	
}
