package com.spring.notes.сontrollers;

import com.spring.notes.entity.Note;
import com.spring.notes.services.NoteCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteCrudService noteCrudService;

    @PostMapping("/list")
    public RedirectView addToList(
            @RequestParam(value = "title", required = false, defaultValue = "Title") String title,
            @RequestParam(value = "content", required = false, defaultValue = "Context") String content
    ) {
        noteCrudService.createAndPutObject(title, content);
        return new RedirectView("/note/list");
    }

    @GetMapping("/list")
    public ModelAndView getListNotes() {
        ModelAndView result = new ModelAndView("notes/mainPage");
        result.addObject("noteMap", noteCrudService.listAll());
        return result;
    }

    @PostMapping("/delete")
    public RedirectView deleteNotesById(
            @RequestParam("idNote") long idNote
    ) {
        try {
            noteCrudService.deleteById(idNote);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return new RedirectView("/note/list");
    }

    @GetMapping("/edit")
    public ModelAndView editNotesById(
            @RequestParam("editNoteId") long editNoteId
    ) {
        ModelAndView result = new ModelAndView("notes/editNote");
        try {
            result.addObject("oldNote", noteCrudService.getById(editNoteId));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @PostMapping("/edit")
    public RedirectView editNotes(
            @RequestParam("editNoteId") long editNoteId,
            @RequestParam(value = "newTitle", required = false, defaultValue = "Title") String newTitle,
            @RequestParam(value = "newContent", required = false, defaultValue = "Context") String newContent
    ) {
        try {
            noteCrudService.update(new Note(editNoteId, newTitle, newContent));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return new RedirectView("/note/list");
    }
}
