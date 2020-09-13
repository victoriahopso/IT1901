module  O3 {

	requires javafx.fxml;
	requires transitive javafx.graphics;
	requires javafx.controls;
	
	exports it1901;

	opens it1901 to javafx.fxml;
}
