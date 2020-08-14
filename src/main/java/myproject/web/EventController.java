package myproject.web;

import myproject.model.binding.EventAddBindingModel;
import myproject.model.service.EventService;
import myproject.repository.EventRepository;
import myproject.service.EventServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    private final ModelMapper modelMapper;

    public EventController(EventService eventService,ModelMapper modelMapper) {
        this.eventService = eventService;
        this.modelMapper = modelMapper;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public String event(Model model) {

        model.addAttribute("eventList",eventService.findAll());

        return "events";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/add")
    public String viewEvent(Model model) {

        if (!model.containsAttribute("eventAddBindingModel")){
            model.addAttribute("eventAddBindingModel",new EventAddBindingModel());
        }

        return "new";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public String createEvent(@Valid @ModelAttribute("eventAddBindingModel") EventAddBindingModel eventAddBindingModel,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("eventAddBindingModel", eventAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.eventAddBindingModel",
                    bindingResult);

            return "redirect:add";
        }

        this.eventService.createOrUpdateEvent(this.modelMapper
                .map(eventAddBindingModel, EventServiceModel.class));

        return "redirect:/";
    }


    @GetMapping("/lists")
    public String getEventList(Model eventList){

        eventList.addAttribute("eventList",eventService.findAll());
        return "event-list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id)
    {
        this.eventService.delete(id);

        return "redirect:/";
    }
}
