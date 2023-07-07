package com.spring.notes.services;

import com.spring.notes.entity.Note;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Data
public class NoteCrudService implements CrudService<Long, Note> {
//    private final NoteRepository noteRepository;
    private final Note note;
    private Map<Long, Note> listNote;

    public NoteCrudService(Note note) {
        this.note = note;
        listNote = new HashMap<>();
    }

    @Override
    public Map<Long, Note> listAll() {
        return listNote;
    }

    @Override
    public Note add(Note entity) {
        Note newNote = new Note();
        newNote.setId(ThreadLocalRandom.current().nextLong(0, 10000 * 100));
        newNote.setTitle(entity.getTitle());
        newNote.setContent(entity.getContent());
        listNote.put(newNote.getId(), newNote);
        return newNote;
    }

    @Override
    public void deleteById(Long id) throws IllegalAccessException {
        Optional.ofNullable(listNote.remove(id)).orElseThrow(IllegalAccessException::new);
    }

    @Override
    public void update(Note entity) throws IllegalAccessException {
        listNote.keySet().stream()
                .filter(entry -> listNote.containsKey(entity.getId()))
                .findFirst()
                .map(vel -> listNote.put(entity.getId(), entity))
                .orElseThrow(IllegalAccessException::new);
    }

    @Override
    public Note getById(Long id) throws IllegalAccessException {
        return Optional.ofNullable(listNote.get(id)).orElseThrow(IllegalAccessException::new);
    }

    public Map<Long, Note> createAndPutObject(String setTitle, String setContent) {
        Note note = new Note();
        note.setTitle(setTitle);
        note.setContent(setContent);
        add(note);
        return listNote;
    }
}
