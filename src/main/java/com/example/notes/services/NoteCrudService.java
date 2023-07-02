package com.example.notes.services;

import com.example.notes.entity.Note;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Data
public class NoteCrudService implements CrudService<Long, Note> {
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
        entity.setId(ThreadLocalRandom.current().nextLong(0, 10000 * 100));
        listNote.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public void deleteById(Long id) throws IllegalAccessException {
        listNote.keySet().stream()
                .filter(key -> listNote.containsKey(id))
                .findFirst()
                .map(del -> listNote.remove(del))
                .orElseThrow(IllegalAccessException::new);
    }

    @Override
    public void update(Note entity) throws IllegalAccessException {
        listNote.keySet().stream()
                .filter(entry -> listNote.containsKey(entity.getId()))
                .findFirst()
                .map(vel -> listNote.replace(entity.getId(), entity))
                .orElseThrow(IllegalAccessException::new);
    }

    @Override
    public Note getById(Long id) throws IllegalAccessException {
        return listNote.keySet().stream()
                .filter(entry -> listNote.containsKey(id))
                .map(vel -> listNote.get(vel))
                .findFirst()
                .orElseThrow(IllegalAccessException::new);
    }

    public static void main(String[] args) throws IllegalAccessException {
        NoteCrudService noteCrudService = new NoteCrudService(new Note());
        Note notes = noteCrudService.getNote();
        notes.setTitle("1234");
        notes.setContent("sss");

        noteCrudService.add(notes);

        System.out.println(noteCrudService.listAll().toString() + "\r\n");

        System.out.println("Note by id: \r\n" + noteCrudService
                .getById(notes.getId()) + "\r\n");


        notes.setTitle("0586750");
        notes.setContent("qqqq");
        noteCrudService.update(notes);
        System.out.println("New note: \r\n" + noteCrudService.listAll().toString() + "\r\n");

        noteCrudService.deleteById(notes.getId());

        System.out.println(noteCrudService.listAll().toString() + "\r\n");
    }
}
