package com.example.statistique.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.statistique.models.Equipe;
import com.example.statistique.service.EquipeService;
import com.example.statistique.service.JoueurService;
import com.example.statistique.service.StatistiqueService;
import com.example.statistique.service.MatchService;

import java.util.List;

@RestController
@RequestMapping("/equipe")
public class EquipeController {
    @Autowired
    private EquipeService equipeService;
    @Autowired
    private JoueurService joueurService;
    @Autowired
    private StatistiqueService statistiqueService;
    @Autowired
    private MatchService matchService;

    @GetMapping("/listeEquipe")
    public List<Equipe> getListeEquipes() {
        List<Equipe> liste = new Equipe().listeEquipe(this.equipeService);
        for (Equipe equipe : liste) {
            int nombre = this.matchService.matchRepository.matchJouer(equipe.getId());
            equipe.setMatch_jouer(nombre);
        }
        return liste;
    }

    @GetMapping("/listeJoueur/{idEquipe}")
    public Equipe getUneEquipe(@PathVariable String idEquipe) {
        return new Equipe(idEquipe).getUneEquipe(this.equipeService, this.joueurService, this.statistiqueService);
    }

}
