package service;

import mapper.ActorMapper;
import model.Actor;
import utils.DBUtils;

import java.util.List;

public class ActorService {

	public List<Actor> getListOfAllActors() {
		String query = "SELECT * FROM actor;";
		return DBUtils.executeQuery(query, new ActorMapper());
	}

	public Actor getAnActorByActorId(int actorId) {
		String query = "SELECT * FROM actor WHERE actor_id = " + actorId;
		List<Actor> actorList = DBUtils.executeQuery(query, new ActorMapper());

		return actorList.isEmpty() ? null : actorList.get(0);
	}

}
