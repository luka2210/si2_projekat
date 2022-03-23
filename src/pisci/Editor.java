package pisci;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Editor extends Pisac{

	public Editor(ResultSet pisac) throws SQLException {
		super(pisac);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getTip() {
		// TODO Auto-generated method stub
		return "Editor";
	}

}