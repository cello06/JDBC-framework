package step_definitions.database_steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Actor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import service.ActorService;

import java.util.List;

public class ActorServiceSteps {

	private static final Logger LOGGER = LogManager.getLogger(ActorServiceSteps.class);

	private List<Actor> actorList;

	private Actor actor;

	private ActorService actorService;

	@Given("the actor service is running")
	public void theActorServiceIsRunning() {
		actorService = new ActorService();
		LOGGER.info("the actor service is running");
	}

	@When("the user send request to database to get list of all actors")
	public void theUserSendRequestToDatabaseToGetListOfAllActors() {
		actorList = actorService.getListOfAllActors();
		LOGGER.info("the user send request to database to get list of all actors");
	}

	@Then("the user should receive a list of all actors")
	public void theUserShouldReceiveAListOfAllActors() {
		Assertions.assertThat(actorList).isNotEmpty().isNotNull();
		LOGGER.debug("the user received a list of all actors");
	}

	@When("the user send request to database to get an actor by {string}")
	public void theUserSendRequestToDatabaseToGetAnActorBy(String actorId) {
		actor = actorService.getAnActorByActorId(Integer.parseInt(actorId));
		LOGGER.info("the user send request to database to get an actor by actor_id --> " + actorId);
	}

	@Then("the user should receive an actor with following details  {string},{string},{string}")
	public void theUserShouldReceiveAnActorWithFollowingDetails(String first_name, String last_name,
			String last_update) {
		Assertions.assertThat(actor).as("Actor could not be received from database!").isNotNull();

		SoftAssertions softAssertions = new SoftAssertions();
		softAssertions.assertThat(actor.getFirstName()).as("Firstname is not matching!").isEqualTo(first_name);
		softAssertions.assertThat(actor.getLastName()).as("Lastname is not matching!").isEqualTo(last_name);
		softAssertions.assertThat(actor.getLastUpdate().toString())
			.as("Last update is not matching!")
			.isEqualTo(last_update);

		softAssertions.assertAll();

		LOGGER.debug("the user received an actor with correct details");
	}

}
