package com.contunder.wankulapi.Application.Controller;

import com.contunder.wankulapi.Application.Model.Card;
import com.contunder.wankulapi.Application.Model.Pageable;
import com.contunder.wankulapi.Application.Service.CardService;
import com.contunder.wankulapi.Data.Payload.CardResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.contunder.wankulapi.Application.Utils.AppConstants.*;

@RestController
@RequestMapping("/api/card")
public class CardController {

    private CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }


    @GetMapping("/")
    public ResponseEntity<CardResponse> getAllCard(
            @RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){

        return ResponseEntity.ok(cardService.getAllCard(new Pageable(pageNo, pageSize, sortBy, sortDir)));
    }

    @GetMapping("/rarity/{rarity}")
    public ResponseEntity<CardResponse> getAllCardByRarety(
            @RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION, required = false) String sortDir,
            @PathVariable(value = "rarity") String rarity
    ){

        return ResponseEntity.ok(cardService.getAllCardByRarity(new Pageable(pageNo, pageSize, sortBy, sortDir), rarity));
    }

    @GetMapping(value = {"/{id}"})
    public ResponseEntity<Card> getActivityByResource(@PathVariable(value = "id") int cardId) {

        return ResponseEntity.ok(cardService.getCardById(cardId));
    }
}
