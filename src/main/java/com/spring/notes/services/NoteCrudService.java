package com.spring.notes.services;


import com.spring.notes.dto.NoteDTO;
import com.spring.notes.entity.Note;
import com.spring.notes.services.repository.NoteRepository;
import com.spring.notes.services.repository.PersonRepository;
import com.spring.notes.services.repository.RoleRepository;
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
    private final PersonRepository useRepository;
    private final RoleRepository roleRepository;

    public Note add(Note entity) {
        Note newNote = new Note();
        newNote.setTitle(entity.getTitle());
        newNote.setContent(entity.getContent());
        newNote.setPerson(entity.getPerson());
        noteRepository.save(newNote);
        return newNote;
    }

    public List<NoteDTO> listAll(Pageable pageable) {
        return noteRepository.findAll(pageable).stream()
                .map(dto -> new NoteDTO(
                        dto.getId(), dto.getTitle(), dto.getContent()
                ))
                .toList();
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
