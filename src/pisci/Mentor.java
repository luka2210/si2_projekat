package pisci;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Mentor extends Pisac{

	public Mentor(ResultSet pisac) throws SQLException {
		super(pisac);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getTip() {
		// TODO Auto-generated method stub
		return "Mentor";
	}

}