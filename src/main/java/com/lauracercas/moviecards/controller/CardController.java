package com.lauracercas.moviecards.controller;

import com.lauracercas.moviecards.model.Card;
import com.lauracercas.moviecards.service.card.CardService;
import com.lauracercas.moviecards.util.Messages;
import org.springframework.ui.Model;
import com.lauracercas.moviecards.model.Actor;
import com.lauracercas.moviecards.model.Movie;
import com.lauracercas.moviecards.service.actor.ActorService;
import com.lauracercas.moviecards.service.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class CardController {


    @Autowired
    private MovieService movieService;
    @Autowired
    private ActorService actorService;
    @Autowired
    private CardService cardService;

    @GetMapping("/registerActorMovie")
    public String showInfoForm(Model model) {
        prepareCardInfoForm(model);

        return "cards/registerActorMovieForm";
    }

    private void prepareCardInfoForm(Model model) {
        List<Actor> actors = actorService.getAllActors();
        List<Movie> movies = movieService.getAllMovies();

        model.addAttribute("actors", actors);
        model.addAttribute("movies", movies);
        model.addAttribute("card", new Card());
    }

    @PostMapping("register")
    public String registerCard(@ModelAttribute Card card,  Model model) {
        String result = cardService.registerActorInMovie(card);

        model.addAttribute("message", result);
        if (!result.equals(Messages.CARD_REGISTRATION_SUCCESS)){
            prepareCardInfoForm(model);
            return "cards/registerActorMovieForm";
        }

        return "index";
    }
}
