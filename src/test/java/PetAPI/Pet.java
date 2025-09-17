package PetAPI;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

	private Integer id;
	private String name;
	private String status;
	private Category category;
	private List<String> photoUrls;
	private List<Tag> tags;

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Category {
		private Integer id;
		private String name;
	}

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Tag {
		private Integer id;
		private String name;
	}

}
