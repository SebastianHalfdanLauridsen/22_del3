package card;

import card.Deck;

import card.GetBankMoneyCard;
//import card.GetOutOfJailCard;
//import card.GetPlayersMoneyCard;
import card.MoveCard;
import card.MoveFieldGetFreeCard;
import card.MoveMaxFiveCard;
import card.MoveOrDrawCard;
//import card.PassPlayerMoveCard;
import card.PayBankCard;

import game.Board;
import game.Game;
import game.Main;
import gui_main.GUI;
import player.Bank;
import player.PlayerManager;

/**
 * //TODO
 */
public class Cards {
    private final Deck deck;
    private final Board board;
    private final Bank bank;
    private final PlayerManager playerManager;
    private final GUI gui;
    private final Game game;

    public Cards(Deck deck, Board board, Bank bank, PlayerManager playerManager, GUI gui, Game game) {
        this.deck = deck;
        this.board = board;
        this.bank = bank;
        this.playerManager = playerManager;
        this.gui = gui;
        this.game = game;
        createCards();
    }

    private void createCards() {

        deck.addCard(Main.getLanguage().getString("cardName"),
                Main.getLanguage().getString("cardDescription1"),
                new MoveCard(23, board, game) //strandpromenaden
        );
        deck.addCard(Main.getLanguage().getString("cardName"),
                Main.getLanguage().getString("cardDescription2"),
                new PayBankCard(2, bank)
        );
        deck.addCard(Main.getLanguage().getString("cardName"),
                Main.getLanguage().getString("cardDescription3"),
                new GetBankMoneyCard(2,bank)
        );
        //TODO
        /*deck.addCard(Main.getLanguage().getString("cardName"),
                Main.getLanguage().getString("cardDescription4"),
                new GetAllPlayerMoneyCard(1, bank, playerManager)
        );*/
        //TODO language resourcebundle
        /*deck.addCard("SJANSE",
                "FRI FELT! Flytt til et oransje felt. Hvis den er tilgjengelig, får du den GRATIS! Ellers må du BETALE husleie til eieren.",
                new MoveFieldGetFreeCard(10,11, gui, game, board, bank) //orange
        );
        deck.addCard("SJANSE",
                "FRI FELT! Flytt til et lyseblått felt. Hvis den er tilgjengelig, får du den GRATIS! Ellers må du BETALE husleie til eieren.",
                new MoveFieldGetFreeCard(4,5, gui, game, board, bank) //light blue
        );
        deck.addCard("SJANSE",
                "FRI FELT! Flytt til et rødt felt. Hvis den er tilgjengelig, får du den GRATIS! Ellers må du BETALE husleie til eieren.",
                new MoveFieldGetFreeCard(13,14, gui, game, board, bank) //red
        );

        deck.addCard("SJANSE",
                "FRI FELT! Flytt til et oransje felt. Hvis den er tilgjengelig, får du den GRATIS! Ellers må du BETALE husleie til eieren.",
                new MoveFieldGetFreeCard(10,11, 19, 20, gui, game, board, bank) //orange and green
        );
        deck.addCard("SJANSE",
                "FRI FELT! Flytt til et oransje felt. Hvis den er tilgjengelig, får du den GRATIS! Ellers må du BETALE husleie til eieren.",
                new MoveFieldGetFreeCard(7,8, 22, 23, gui, game, board, bank) //pink and darkblue
        );
        deck.addCard("SJANSE",
                "FRI FELT! Flytt til et oransje felt. Hvis den er tilgjengelig, får du den GRATIS! Ellers må du BETALE husleie til eieren.",
                new MoveFieldGetFreeCard(4,5, 12, 13, gui, game, board, bank) //light blue and red
        );
        deck.addCard("SJANSE",
                "FRI FELT! Flytt til et oransje felt. Hvis den er tilgjengelig, får du den GRATIS! Ellers må du BETALE husleie til eieren.",
                new MoveFieldGetFreeCard(1,2, 16, 17, gui, game, board, bank) //brown and yellow
        );*/

        //TODO language resourcebundle
        deck.addCard("SJANSE",
                "Flytt opptil 5 felter.",
                new MoveMaxFiveCard(gui,board, game)
        );

        //TODO language resource bundle
        deck.addCard("SJANSE",
                "Move or draw",
                new MoveOrDrawCard(1, gui, board, game)
        );

        deck.addCard("SJANSE",
                "Move to start get M2",
                new MoveCard(0, board, game) //start
        );

    }
}
