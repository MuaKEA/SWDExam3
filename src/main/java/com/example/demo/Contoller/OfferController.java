package com.example.demo.Contoller;

import com.example.demo.Model.Offer;
import com.example.demo.Repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OfferController {
@Autowired
private OfferRepository offerRepository;

    @GetMapping("/opretTilbud")
    public String createOffer(Model model){
        model.addAttribute("offer", new Offer());

        return "createOffer";
    }

    @PostMapping("/opretTilbud")
    public String createOffer(Offer offer){
        offerRepository.save(offer);
        return "redirect:/visTilbud";
    }

    @GetMapping("/visTilbud")
    public String getOffer(Model model){
        model.addAttribute("offerList", offerRepository.findAll());

        return "showOffer";
    }

    @GetMapping("/redigerTilbud")
    public String editOffer(@RequestParam(value = "id") Long id, Model model){
        model.addAttribute("offer", offerRepository.findById(id));
        return "editOffer";
    }

    @PostMapping("/redigerTilbud")
    public String editOffer(@ModelAttribute("offerDelete") Offer offer){
        offerRepository.deleteById(offer.getId());
        offerRepository.save(offer);
        return "redirect:/visTilbud";
    }

    @GetMapping("/sletTilbud")
    public String deleteOffer(@RequestParam(value = "id") Long id){
        offerRepository.deleteById(id);
        return "redirect:/visTilbud";
    }

}
