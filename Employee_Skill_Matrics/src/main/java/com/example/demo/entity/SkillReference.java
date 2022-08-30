package com.example.demo.entity;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "hr1_skill_ref")

public class SkillReference {

	@Id
	@Column(name = "skill_id")
	int skillId;
	
	@Column(name = "skill_type")
	String skillType;
	
	@Column(name = "skill_name")
	String skillName;
	
	@Column(name = "created_date")
	@DateTimeFormat(iso = ISO.DATE)
	Date createdDate;
	
	@Column(name = "created_by")
	String createdBy;
	
	@Column(name = "created_time")
	@DateTimeFormat(iso = ISO.TIME)
	Time createdTime;

}
