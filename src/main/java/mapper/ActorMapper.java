package mapper;

import model.Actor;
import utils.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActorMapper implements RowMapper<Actor> {

	@Override
	public Actor mapRow(ResultSet rs) throws SQLException {
		return new Actor(rs.getInt("actor_id"), rs.getString("first_name"), rs.getString("last_name"),
				rs.getTimestamp("last_update"));
	}

}
