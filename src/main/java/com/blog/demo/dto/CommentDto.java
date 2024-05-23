package com.blog.demo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
	
	private Long id;
	private String name;
	private String email;
	private String content;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
	

}
