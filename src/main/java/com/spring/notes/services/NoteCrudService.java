package com.spring.notes.services;


import com.spring.notes.dto.NoteDTO;
import com.spring.notes.entity.Note;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Data
public class NoteCrudService {
    private final NoteRepository noteRepository;

    public List<NoteDTO> listAll(Pageable pageable) {
        return noteRepository.findAll(pageable).stream()
                .map(dto -> new NoteDTO(
                        dto.getId(), dto.getTitle(), dto.getContent()
                ))
                .toList();
    }

    public Note add(Note entity) {
        Note newNote = new Note();
        newNote.setTitle(entity.getTitle());
        newNote.setContent(entity.getContent());
        noteRepository.save(newNote);
        return newNote;
    }

    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }

    public void update(Note entity) {
        noteRepository.getReferenceById(entity.getId());
        noteRepository.save(entity);
    }

    public Note getById(Long id)  {
        return noteRepository.getReferenceById(id);
    }
}
