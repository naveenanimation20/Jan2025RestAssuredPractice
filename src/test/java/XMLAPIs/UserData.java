package XMLAPIs;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "objects")
public class UserData {

	@JacksonXmlProperty(isAttribute = true, localName = "type")
	private String type;

	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "object")
	private List<ObjectData> objects;

	@Data
	public static class ObjectData {

		@JacksonXmlProperty(localName = "name")
		private String name;

		@JacksonXmlProperty(localName = "gender")
		private String gender;

		@JacksonXmlProperty(localName = "email")
		private String email;

		@JacksonXmlProperty(localName = "status")
		private String status;

		@JacksonXmlProperty(localName = "id")
		private IDWrap id;

		@Data
		public static class IDWrap {

			@JacksonXmlText
			private int value;

			@JacksonXmlProperty(isAttribute = true, localName = "type")
			private String type;
		}

	}

}
