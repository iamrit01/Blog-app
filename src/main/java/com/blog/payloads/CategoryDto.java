package com.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	private Integer categoryId;
	
	@NotBlank
	@Size(min = 4,message = "Mini size of title is 4")
	private String categoryTitle;
	
	@NotBlank
	@Size(min=10, message = "Mini description required 10 chars")
    private String categoryDescription;
}
