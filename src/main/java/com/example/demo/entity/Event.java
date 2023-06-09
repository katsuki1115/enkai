package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "events")
public class Event extends AbstractEntity {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 255, nullable = false)
	@NotEmpty(message = "イベント名がありません")
	private String name;
	
	@Column(length = 255, nullable = false)
	@NotEmpty(message = "詳細がありません")
	private String detail;
	
	@Column(nullable = false)
	@NotEmpty(message = "最大参加者が設定されていません")
	@Min(value = 0, message = "参加者数は0以上の整数を入力してください")
	private String max_participant;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	@NotNull(message = "カテゴリは必須入力です")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@NotNull(message = "ユーザは必須入力です")
	private User user;
	
	@OneToMany(mappedBy = "event", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	List<EventUser> eventuser;
	
}
