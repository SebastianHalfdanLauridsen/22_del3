package card;

import game.Board;
import game.Game;
import game.Main;
import gui_main.GUI;
import player.Bank;
import player.PlayerManager;

/**
 * The cards class creates all the cards in on place and adds them to the deck.
 */
public record Cards(Deck deck, Board board, Bank bank, PlayerManager playerManager,
                    GUI gui, Game game) {
    public Cards(Deck deck, Board board, Bank bank, PlayerManager playerManager, GUI gui, Game game) {
        this.deck = deck;
        this.board = board;
        this.bank = bank;
        this.playerManager = playerManager;
        this.gui = gui;
        this.game = game;
        createCards();
    }

    /**
     * Creates all the cards and adds them to the Deck
     */
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
                new GetBankMoneyCard(2, bank)
        );

        deck.addCard(Main.getLanguage().getString("cardName"),
                Main.getLanguage().getString("cardDescription4"),
                new GetPlayersMoneyCard(1, bank, playerManager)
        );

        deck.addCard(Main.getLanguage().getString("cardName"),
                Main.getLanguage().getString("cardDescription5"),
                new MoveFieldGetFreeCard(10, 11, gui, game, board, bank) //orange
        );
        deck.addCard(Main.getLanguage().getString("cardName"),
                Main.getLanguage().getString("cardDescription6"),
                new MoveFieldGetFreeCard(4, 5, gui, game, board, bank) //light blue
        );
        deck.addCard(Main.getLanguage().getString("cardName"),
                Main.getLanguage().getString("cardDescription7"),
                new MoveFieldGetFreeCard(13, 14, gui, game, board, bank) //red
        );

        deck.addCard(Main.getLanguage().getString("cardName"),
                Main.getLanguage().getString("cardDescription8"),
                new MoveFieldGetFreeCard(10, 11, 19, 20, gui, game, board, bank) //orange and green
        );
        deck.addCard(Main.getLanguage().getString("cardName"),
                Main.getLanguage().getString("cardDescription9"),
                new MoveFieldGetFreeCard(7, 8, 22, 23, gui, game, board, bank) //pink and darkblue
        );
        deck.addCard(Main.getLanguage().getString("cardName"),
                Main.getLanguage().getString("cardDescription10"),
                new MoveFieldGetFreeCard(4, 5, 13, 14, gui, game, board, bank) //light blue and red
        );
        deck.addCard(Main.getLanguage().getString("cardName"),
                Main.getLanguage().getString("cardDescription11"),
                new MoveFieldGetFreeCard(1, 2, 16, 17, gui, game, board, bank) //brown and yellow
        );

        deck.addCard(Main.getLanguage().getString("cardName"),
                Main.getLanguage().getString("cardDescription12"),
                new MoveMaxFiveCard(gui, board, game)
        );

        deck.addCard(Main.getLanguage().getString("cardName"),
                Main.getLanguage().getString("cardDescription13"),
                new MoveOrDrawCard(1, gui, board, game)
        );

        deck.addCard(Main.getLanguage().getString("cardName"),
                Main.getLanguage().getString("cardDescription14"),
                new MoveCard(0, board, game) //start
        );

        deck.addCard(Main.getLanguage().getString("cardName"),
                Main.getLanguage().getString("cardDescription15"),
                new GetOutOfJailCard());
    }
}
