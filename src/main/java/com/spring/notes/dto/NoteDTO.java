package com.spring.notes.dto;

public record NoteDTO(
        Long id,
        String title,
        String content
) { }
