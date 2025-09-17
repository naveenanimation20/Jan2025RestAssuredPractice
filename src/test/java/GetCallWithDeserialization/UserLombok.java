package GetCallWithDeserialization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLombok {

	private int id;
	private String name;
	private String email;
	private String gender;
	private String status;
	

}
