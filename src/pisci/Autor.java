package pisci;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Autor extends Pisac{

	public Autor(ResultSet pisac) throws SQLException {
		super(pisac);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getTip() {
		// TODO Auto-generated method stub
		return "Autor";
	}

	
}
