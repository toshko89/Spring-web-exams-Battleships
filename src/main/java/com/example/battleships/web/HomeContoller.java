package com.example.battleships.web;

import com.example.battleships.model.Ship;
import com.example.battleships.model.dto.FightDTO;
import com.example.battleships.service.ShipService;
import com.example.battleships.session.LoggedUserSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Set;

@Controller
public class HomeContoller {

    private final LoggedUserSession loggedUserSession;
    private final ShipService shipService;

    public HomeContoller(LoggedUserSession loggedUserSession, ShipService shipService) {
        this.loggedUserSession = loggedUserSession;
        this.shipService = shipService;
    }

    @ModelAttribute
    public FightDTO initFightDTO() {
        return new FightDTO();
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model){

        if(!loggedUserSession.isLoggeinIn()){
            return "redirect:/login";
        }

        Set<Ship> ownShips = this.shipService.findOwnShips(loggedUserSession.getId());
        Set<Ship> otherShips = this.shipService.findOtherShips(loggedUserSession.getId());
        List<Ship> allShips = this.shipService.findAllShips();

        model.addAttribute("ownShips",ownShips);
        model.addAttribute("otherShips",otherShips);
        model.addAttribute("allShips",allShips);

        return "home";
    }

    @PostMapping("/attack")
    public String attack(@ModelAttribute("fightDTO") FightDTO fightDTO){

        if (!loggedUserSession.isLoggeinIn()) {
            return "redirect:/login";
        }
        this.shipService.attack(fightDTO);
        return "redirect:/home";
    }


}
