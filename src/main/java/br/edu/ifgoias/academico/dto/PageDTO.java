package br.edu.ifgoias.academico.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO<T> {
	private List<T> content;
	private int totalPages;
	private long totalElements;
	private int size;
	private int number;
}
