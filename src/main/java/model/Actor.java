package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Actor {

	private int actorId;

	private String firstName;

	private String lastName;

	private Timestamp lastUpdate;

}
