package tw.com.ispan.domain;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "recipe_message")
public class RecipeMessageBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "message_id")
	private Integer messageId;

	@Column(name = "recipe_id")
	private Integer recipeId;

	@Column(name = "id")
	private Integer id;

	@Column(name = "contributor_id")
	private Integer contributorId;

	@Column(name = "message_datetime")
	private LocalDateTime messageDatetime;

	@Column(name = "content", columnDefinition = "nvarchar")
	private String content;

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public Integer getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Integer recipeId) {
		this.recipeId = recipeId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getContributorId() {
		return contributorId;
	}

	public void setContributorId(Integer contributorId) {
		this.contributorId = contributorId;
	}

	public LocalDateTime getMessageDatetime() {
		return messageDatetime;
	}

	public void setMessageDatetime(LocalDateTime messageDatetime) {
		this.messageDatetime = messageDatetime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "RecipeMessageBean [messageId=" + messageId + ", recipeId=" + recipeId + ", id=" + id
				+ ", contributorId=" + contributorId + ", messageDatetime=" + messageDatetime + ", content=" + content
				+ "]";
	}

}
