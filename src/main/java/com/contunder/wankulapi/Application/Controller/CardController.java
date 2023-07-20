package com.contunder.wankulapi.Application.Controller;

import com.contunder.wankulapi.Application.Model.Card;
import com.contunder.wankulapi.Application.Service.CardService;
import com.contunder.wankulapi.Data.Payload.CardResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.contunder.wankulapi.Application.Utils.AppConstants.*;

@RestController
@RequestMapping("/api/card")
public class CardController {

    private CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }


    @GetMapping("/all")
    public ResponseEntity<CardResponse> getAllResources(
            @RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){

        return ResponseEntity.ok(cardService.getAllCard(pageNo, pageSize, sortBy, sortDir));
    }

    @GetMapping(value = {"/{id}"})
    public ResponseEntity<Card> getActivityByResource(@PathVariable(value = "id") int cardId) {

        return ResponseEntity.ok(cardService.getCardById(cardId));
    }
}
