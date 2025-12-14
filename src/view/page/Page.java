package view.page;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public abstract class Page extends BorderPane {

	 protected Page() {
        start();
        setLayout();
        setStyle();
        setEvent();
    }
	 
    protected abstract void start();
	protected abstract void setLayout();
	protected abstract void setStyle();
	protected abstract void setEvent();
	
}
