package condition;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
public class EmailCondition {

	
	@NotEmpty(message="이메일을 입력하세요")
	@Email(message="이메일 형식으로 입력하세요")
	private String email;
	private Integer dice;
	private Integer userInput;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getDice() {
		return dice;
	}

	public void setDice(Integer dice) {
		this.dice = dice;
	}

	public Integer getUserInput() {
		return userInput;
	}

	public void setUserInput(Integer userInput) {
		this.userInput = userInput;
	}
	
	
	
}
