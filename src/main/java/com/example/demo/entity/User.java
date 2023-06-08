package com.example.demo.entity;

import org.hibernate.sql.Update;

import com.example.demo.common.PasswordHasher;
import com.example.demo.common.ValidationGroups.Create;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User extends AbstractEntity {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 255, nullable = false, unique = true)
	@NotEmpty(groups = { Create.class, Update.class }, message = "メールアドレスは必須要項です")
	@Email(groups = { Create.class, Update.class }, message = "メールアドレスの形式が不正です")
	private String email;

	@Column(length = 255, nullable = false)
	@NotEmpty(groups = { Create.class, Update.class }, message = "パスワードは必須項目です")
	private String password;

	//平文を暗号文にしてpasswordに設定する
	public void encodePassword(String rawPassword) {
		if (!"".equals(rawPassword)) {
			this.setPassword(PasswordHasher.create(rawPassword));
		}
	}
	
	
}
