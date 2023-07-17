package com.spring.notes.сontrollers;

import com.spring.notes.entity.Note;
import com.spring.notes.services.NoteCrudService;
import com.spring.notes.services.repository.PersonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteCrudService noteCrudService;
    private final PersonRepository personRepository;

    @Transactional
    @PostMapping("/list")
    public RedirectView addToDataBase(
            @RequestParam(value = "title", required = false, defaultValue = "Title") String title,
            @RequestParam(value = "content", required = false, defaultValue = "Context") String content,
            Authentication authentication
    ) {
        noteCrudService.add(new Note(null, title, content,
                personRepository.findPersonByUsername(authentication.getName())
                        .orElseThrow()));
        return new RedirectView("/note/list");
    }

    @GetMapping("/list")
    public ModelAndView getNotes(Pageable pageable) {
        return new ModelAndView("notes/mainPage")
                .addObject("listNotes", noteCrudService.listAll(pageable));
    }

    @Transactional
    @PostMapping("/delete")
    public RedirectView deleteNotesById(@RequestParam("idNote") long idNote) {
        noteCrudService.deleteById(idNote);
        return new RedirectView("/note/list");
    }

    @GetMapping("/edit/{editNoteId}")
    public ModelAndView editNotesById(@PathVariable("editNoteId") long editNoteId) {
        return new ModelAndView("notes/editNote")
                .addObject("oldNote", noteCrudService.getById(editNoteId));
    }

    @Transactional
    @PostMapping("/edit/{editNoteId}")
    public RedirectView editNotes(
            @PathVariable("editNoteId") long editNoteId,
            @RequestParam(value = "newTitle", required = false, defaultValue = "Title") String newTitle,
            @RequestParam(value = "newContent", required = false, defaultValue = "Context") String newContent
    ) {
        noteCrudService.update(new Note(editNoteId, newTitle, newContent, null));
        return new RedirectView("/note/list");
    }
}
