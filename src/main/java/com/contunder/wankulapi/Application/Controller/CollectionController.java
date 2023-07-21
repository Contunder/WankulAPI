package com.contunder.wankulapi.Application.Controller;

import com.contunder.wankulapi.Application.Security.JwtAuthenticationFilter;
import com.contunder.wankulapi.Application.Security.JwtTokenProvider;
import com.contunder.wankulapi.Application.Service.DeckService;
import com.contunder.wankulapi.Data.Payload.CardResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import static com.contunder.wankulapi.Application.Utils.AppConstants.*;
import static com.contunder.wankulapi.Application.Utils.AppConstants.DEFAULT_SORT_DIRECTION;

@RestController
@RequestMapping("/api/collection")
public class CollectionController {

    private DeckService deckService;
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    private JwtTokenProvider jwtTokenProvider;

    public CollectionController(DeckService deckService, JwtAuthenticationFilter jwtAuthenticationFilter, JwtTokenProvider jwtTokenProvider) {
        this.deckService = deckService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/")
    public ResponseEntity<CardResponse> getAllResources(
            @RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION, required = false) String sortDir,
            HttpServletRequest request
    ){
        String token = jwtAuthenticationFilter.getTokenFromRequest(request);
        String email = jwtTokenProvider.getEmail(token).orElseThrow(() ->
                new UsernameNotFoundException("User not found with email: "));


        return ResponseEntity.ok(deckService.getAllMyCard(pageNo, pageSize, sortBy, sortDir, email));
    }

    @PostMapping("/add/{cardNumber}")
    public ResponseEntity<String> getActivityByResource(@PathVariable(value = "cardNumber") int cardNumber, HttpServletRequest request) {
        String token = jwtAuthenticationFilter.getTokenFromRequest(request);
        String email = jwtTokenProvider.getEmail(token).orElseThrow(() ->
                new UsernameNotFoundException("User not found with email: "));

        return ResponseEntity.ok(deckService.addCardByCardNumber(cardNumber, email));
    }
}
