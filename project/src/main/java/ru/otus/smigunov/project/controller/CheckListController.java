package ru.otus.smigunov.project.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.smigunov.project.domain.CheckList;
import ru.otus.smigunov.project.repositories.CheckListRepository;

@Controller
@AllArgsConstructor
public class CheckListController {

    private final CheckListRepository checkListRepository;

    @GetMapping("/checkLists")
    public String getCheckList(
            @RequestParam("userid") Long userid,
            Model model) {
        model.addAttribute("checkLists", checkListRepository.findByUserid(userid));
        model.addAttribute("userid", userid);
        return "checkLists";
    }

    @GetMapping("/addCheckList")
    public String getEditPage(
            @RequestParam("userid") Long userid,
            Model model) {
        model.addAttribute("checkList", CheckList.builder().userid(userid).build());
        return "editCheckList";
    }

    @PostMapping(value = "/checkListSave",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String saveCheckList(
            @ModelAttribute("checkList") CheckList checkList,
            Model model
    ) {
        checkListRepository.save(checkList);
        model.addAttribute("checkLists", checkListRepository.findByUserid(checkList.getUserid()));
        model.addAttribute("userid", checkList.getUserid());
        return "checkLists";
    }

    @GetMapping("/deletecheckList")
    public String deleteCheckList(
            @RequestParam("id") Long id,
            @RequestParam("userid") Long userid,
            Model model
    ) {
        checkListRepository.deleteById(id);
        model.addAttribute("checkLists", checkListRepository.findByUserid(userid));
        model.addAttribute("userid", userid);
        return "checkLists";
    }
}
