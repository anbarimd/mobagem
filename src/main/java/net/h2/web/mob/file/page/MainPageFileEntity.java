package net.h2.web.mob.file.page;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import net.h2.web.core.base.server.entity.BaseEntity;
import net.h2.web.mob.mainpage.enums.MainPageType;

@Entity
@Table(name = "MOB_MAIN_PAGE_FILE")
public class MainPageFileEntity extends BaseEntity<Long> {

	private String title;
	private byte[] photo;
	private MainPageType type;
	private Integer colIndex;
	private String description;
	private Integer fileSize;
	private String fileExtension;
	private Date createdDate;

	@Override
	@Id
	@GeneratedValue
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Lob
	@Column(length = Integer.MAX_VALUE)
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public MainPageType getType() {
		return type;
	}

	public void setType(MainPageType type) {
		this.type = type;
	}

	public Integer getColIndex() {
		return colIndex;
	}

	public void setColIndex(Integer colIndex) {
		this.colIndex = colIndex;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
