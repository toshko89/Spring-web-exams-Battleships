package com.example.battleships.web;

import com.example.battleships.model.dto.ShipDTO;
import com.example.battleships.service.ShipService;
import com.example.battleships.session.LoggedUserSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ShipController {

    private final LoggedUserSession loggedUserSession;
    private final ShipService shipService;

    public ShipController(LoggedUserSession loggedUserSession, ShipService shipService) {
        this.loggedUserSession = loggedUserSession;
        this.shipService = shipService;
    }

    @ModelAttribute
    public ShipDTO initShipDTO() {
        return new ShipDTO();
    }

    @GetMapping("/ship-add")
    public String shipAdd(){
        if (!loggedUserSession.isLoggeinIn()) {
            return "redirect:/login";
        }
        return "ship-add";
    }

    @PostMapping("/ship-add")
    public String shipAdd(@Valid ShipDTO shipDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if (!loggedUserSession.isLoggeinIn()) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("ShipDTO", shipDTO);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.shipDTO", bindingResult);

            return "redirect:/ship-add";
        }

        this.shipService.addShip(shipDTO);

        return "redirect:/home";

    }
}
